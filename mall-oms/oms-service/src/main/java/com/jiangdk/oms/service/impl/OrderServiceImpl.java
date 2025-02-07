package com.jiangdk.oms.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangdk.common.exception.BizException;
import com.jiangdk.common.result.Result;
import com.jiangdk.common.util.IdGenerator;
import com.jiangdk.oms.mapper.OrderItemMapper;
import com.jiangdk.oms.mapper.OrderMapper;
import com.jiangdk.oms.pojo.entity.Order;
import com.jiangdk.oms.pojo.entity.OrderItem;
import com.jiangdk.oms.pojo.form.OrderForm;
import com.jiangdk.oms.pojo.vo.OrderItemVO;
import com.jiangdk.oms.pojo.vo.OrderVO;
import com.jiangdk.oms.service.OrderService;
import com.jiangdk.pms.dto.SkuDTO;
import com.jiangdk.pms.dto.StockDTO;
import com.jiangdk.pms.feign.SkuFeignClient;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private SkuFeignClient skuFeignClient;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private String key() {
        Long userId = StpUtil.getLoginIdAsLong();
        return "cart:" + userId;
    }

    /**
     * 确认订单
     * 从商品详情页面下单
     *
     * @param skuId
     * @param count
     * @return
     */
    @Override
    public OrderVO orderConfirm(Long skuId, Integer count) {
        OrderVO orderVO = new OrderVO();
        String orderSn = IdGenerator.snowflakeIdStr();
        // 设置订单编号
        orderVO.setOrderSn(orderSn);
        OrderItemVO orderItemVO = new OrderItemVO();
        orderItemVO.setCount(count);
        // 远程调用商品微服务，获取商品最新的信息
        Result<SkuDTO> result = skuFeignClient.getSkuById(skuId);
        if (result.isError()) {
            throw new BizException(result.getCode(), result.getMsg());
        }
        SkuDTO skuDTO = result.getData();
        BeanUtils.copyProperties(skuDTO, orderItemVO);
        // 设置订单明细
        orderVO.setOrderItems(ListUtil.of(orderItemVO));
        // 存储订单token
        redisTemplate.opsForValue().set("order:token:" + orderSn, orderSn, Duration.ofMinutes(1));
        return orderVO;
    }

    /**
     * 确认订单
     * 从购物车下单
     *
     * @return
     */
    @Override
    public OrderVO orderConfirm() {
        // 获取购物车中选中的商品
        // 购物车的数据在redis中，可以直接从redis中拿
        OrderVO orderVO = new OrderVO();
        // 设置订单编号
        String orderSn = IdGenerator.snowflakeIdStr();
        orderVO.setOrderSn(orderSn);
        // 订单明细
        List<OrderItemVO> orderItemVOList = new ArrayList<>();
        Set<String> goodsKeys = redisTemplate.opsForZSet().range(key(), 0, -1);
        if (CollectionUtil.isEmpty(goodsKeys)) {
            throw new BizException(HttpStatus.HTTP_NOT_FOUND, "购物车中没商品");
        }
        for (String goodsKey : goodsKeys) {
            Boolean checked = (Boolean) redisTemplate.opsForHash().get(goodsKey, "checked");
            if (checked) {
                String substring = goodsKey.substring(goodsKey.lastIndexOf(":") + 1);
                Long skuId = Long.parseLong(substring);
                Integer count = (Integer) redisTemplate.opsForHash().get(goodsKey, "count");
                OrderItemVO orderItemVO = new OrderItemVO();
                orderItemVO.setCount(count);
                // 远程调用商品微服务，获取商品最新的信息
                Result<SkuDTO> result = skuFeignClient.getSkuById(skuId);
                if (result.isError()) {
                    throw new BizException(result.getCode(), result.getMsg());
                }
                SkuDTO skuDTO = result.getData();
                BeanUtils.copyProperties(skuDTO, orderItemVO);
                orderItemVOList.add(orderItemVO);
            }
        }
        if (orderItemVOList.isEmpty()) {
            throw new BizException(HttpStatus.HTTP_NOT_FOUND, "购物车中没有选中的商品");
        }
        // 设置订单明细
        orderVO.setOrderItems(orderItemVOList);
        redisTemplate.opsForValue().set("order:token:" + orderSn, orderSn, Duration.ofMinutes(1));
        return orderVO;
    }

    /**
     * 提交订单
     *
     * @param orderForm
     * @return
     */
    @Override
    @Transactional
    public void orderSubmit(OrderForm orderForm) {
        // 防止订单重复提交和
        String orderSn = orderForm.getOrderSn();
        String token = (String) redisTemplate.opsForValue().get("order:token:" + orderSn);
        // 如果订单编号存在。
        if (token == null) {
            throw new BizException(HttpStatus.HTTP_BAD_REQUEST, "订单无效，请重新下单");
        }
        // 处理完这个订单，就把订单编号从redis中删除，失效掉这个订单
        redisTemplate.delete("order:token:" + orderSn);
        Long orderId = IdGenerator.snowflakeId();
        Order order = new Order();
        BeanUtils.copyProperties(orderForm, order);
        order.setId(orderId);// 订单id
        order.setUserId(StpUtil.getLoginIdAsLong());
        order.setStatus(1); // 待付款
        // 收集多个订单详情表
        List<OrderItem> orderItems = new ArrayList<>();
        orderForm.getOrderItems().forEach(orderItemForm -> {
            Result<SkuDTO> result = skuFeignClient.getSkuById(orderItemForm.getSkuId());
            if (result.isError()) {
                throw new BizException(HttpStatus.HTTP_NOT_FOUND, "购买的商品不存在或已下架");
            }
            SkuDTO skuDTO = result.getData();
            // 校验商品价格是否发生变化
            if (!orderItemForm.getPrice().equals(skuDTO.getPrice())) {
                throw new BizException(HttpStatus.HTTP_BAD_REQUEST, "购买的商品价格【" + skuDTO.getSpuName() + skuDTO.getSkuName() + "】发生变化,请重新下单");

            }
            OrderItem orderItem = new OrderItem();
            BeanUtils.copyProperties(skuDTO, orderItem);
            orderItem.setId(IdGenerator.snowflakeId());
            orderItem.setOrderId(orderId);
            orderItem.setCount(orderItemForm.getCount());
            orderItems.add(orderItem);
        });
        // 扣库存
        StockDTO stockDTO = new StockDTO();
        stockDTO.setOrderSn(orderSn);
        List<StockDTO.LockedSku> collect = orderForm.getOrderItems().stream().map(orderItemForm -> {
            StockDTO.LockedSku lockedSku = new StockDTO.LockedSku();
            lockedSku.setSkuId(orderItemForm.getSkuId());
            lockedSku.setCount(orderItemForm.getCount());
            return lockedSku;
        }).collect(Collectors.toList());
        stockDTO.setLockedSkus(collect);
        Result result = skuFeignClient.lockStock(stockDTO);
        if (result.isError()) {
            throw new BizException(result.getCode(), result.getMsg());
        }
        // 计算商品总金额
        Integer totalAmount = orderItems.stream()
                .map(orderItem -> orderItem.getPrice() * orderItem.getCount())
                .reduce(Integer::sum).get();
        if (!orderForm.getTotalAmount().equals(totalAmount)) {
            throw new BizException(HttpStatus.HTTP_BAD_REQUEST, "商品总价格不正确,请重新下单");
        }
        // 计算商品的应付款金额
        Integer payMount = totalAmount - 0; // 如果有优惠啥的可以在这里面减去
        if (!orderForm.getPayAmount().equals(payMount)) {
            throw new BizException(HttpStatus.HTTP_BAD_REQUEST, "应付款金额不正确,请重新下单");
        }
        // 入库订单表
        this.save(order);
        // 入库订单明细表
        orderItems.forEach(orderItem -> orderItemMapper.insert(orderItem));
        // 发送订单id到消息队列
        rabbitTemplate.convertAndSend("oms.order", "order.cancel", orderId, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setDelay(30 * 60 * 1000); // 30分钟有效期
                return message;
            }
        }, new CorrelationData(orderSn));
    }

    /**
     * 取消订单
     *
     * @param orderId
     */
    @Override
    public void orderCancel(Long orderId) {
        // 查到订单
        Order order = this.getById(orderId);
        // 订单存在，并且是待付款状态
        if (order != null && order.getStatus() == 1) {
            order.setStatus(5);
            this.updateById(order);
            //  释放库存的占用
            skuFeignClient.unlockStock(order.getOrderSn());
        }
    }

    //    @Override
//    public List<OrderVO> getOrders() {
//        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
//        orderQueryWrapper.eq("userId", StpUtil.getLoginIdAsLong());
//        List<Order> orders = orderMapper.selectList(orderQueryWrapper);
//        orders.forEach(order -> {
//            QueryWrapper<OrderItem> orderItemQueryWrapper = new QueryWrapper<>();
//            orderItemQueryWrapper.eq("orderId", order.getId());
//            List<OrderItem> orderItems = orderItemMapper.selectList(orderItemQueryWrapper);
//        });
//        orders.stream()
//        OrderVO orderVO = new OrderVO();
//
//        return null;
//    }
    @Override
    public List<OrderVO> getOrders() {
        // 1. 根据当前用户ID查询所有订单
        Long userId = StpUtil.getLoginIdAsLong();
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("user_id", userId);
        List<Order> orders = orderMapper.selectList(orderQueryWrapper);

        if (orders.isEmpty()) {
            return Collections.emptyList();
        }

        // 2. 收集所有订单的ID
        List<Long> orderIds = orders.stream()
                .map(Order::getId)
                .collect(Collectors.toList());

        // 3. 批量查询所有相关的订单项
        QueryWrapper<OrderItem> orderItemQueryWrapper = new QueryWrapper<>();
        orderItemQueryWrapper.in("order_id", orderIds);
        List<OrderItem> allOrderItems = orderItemMapper.selectList(orderItemQueryWrapper);

        // 4. 将订单项按照订单ID分组
        Map<Long, List<OrderItem>> orderItemsMap = allOrderItems.stream()
                .collect(Collectors.groupingBy(OrderItem::getOrderId));

        // 5. 将订单和对应的订单项转换为 OrderVO
        List<OrderVO> orderVOList = orders.stream()
                .map(order -> {
                    OrderVO orderVO = new OrderVO();
                    orderVO.setOrderSn(order.getOrderSn());
                    orderVO.setStatus(order.getStatus());
                    // 设置对应的订单项
                    List<OrderItem> items = orderItemsMap.getOrDefault(order.getId(), Collections.emptyList());
                    List<OrderItemVO> itemVOs = items.stream()
                            .map(item -> {
                                OrderItemVO itemVO = new OrderItemVO();
                                BeanUtils.copyProperties(item, itemVO);
                                return itemVO;
                            }).collect(Collectors.toList());
                    orderVO.setOrderItems(itemVOs);
                    return orderVO;
                })
                .collect(Collectors.toList());

        return orderVOList;
    }
}
