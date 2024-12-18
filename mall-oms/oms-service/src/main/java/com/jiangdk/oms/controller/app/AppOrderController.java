package com.jiangdk.oms.controller.app;

import cn.hutool.http.HttpStatus;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jiangdk.common.exception.BizException;
import com.jiangdk.common.result.Result;
import com.jiangdk.oms.mapper.OrderMapper;
import com.jiangdk.oms.pojo.entity.Order;
import com.jiangdk.oms.pojo.form.OrderForm;
import com.jiangdk.oms.pojo.vo.OrderVO;
import com.jiangdk.oms.service.OrderService;
import com.jiangdk.oms.service.PayService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 应用端/订单
 */
@RestController
@RequestMapping("/api/oms/order")
public class AppOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AlipayClient alipayClient;
    @Value("${alipay.return-url}")
    private String returnUrl;
    @Value("${alipay.notify-url}")
    private String notifyUrl;

    /**
     * 确认订单【从商品详情页下单】
     *
     * @return
     */
    @GetMapping("/confirm")
    public Result<OrderVO> confirmOrder(
            @NotNull Long skuId,
            @NotNull Integer count) {
        return Result.success(orderService.orderConfirm(skuId, count));
    }

    /**
     * 确认订单【从购物车下单】
     *
     * @return
     */
    @GetMapping("/confirmCart")
    public Result<OrderVO> confirmOrder() {
        return Result.success(orderService.orderConfirm());
    }
    /**
     * 提交订单
     *
     * @return
     */
    @PostMapping("/submit")
    public Result submitOrder(
            @RequestBody @Validated OrderForm orderForm
            ) {
        orderService.orderSubmit(orderForm);
        return Result.success();
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @PutMapping("/cancel")
    public Result cancelOrder(Long orderId){
        orderService.orderCancel(orderId);
        return Result.success();
    }

    /**
     * 支付接口
     * @param orderSn
     * @param
     * @throws AlipayApiException
     * @throws IOException
     */
    @GetMapping( "/toPay")
    public void toPay
    (
        @RequestParam String orderSn,
        HttpServletResponse httpServletResponse
    ) throws AlipayApiException, IOException {
        // 根据订单编号获取订单信息
        Order order = orderMapper.selectOne(new LambdaQueryWrapper<Order>().eq(Order::getOrderSn, orderSn));
        if (order == null || !order.getStatus().equals(1) ) {
            throw new BizException(HttpStatus.HTTP_BAD_REQUEST, "订单不存在或已支付");
        }
        // 构造请求参数以调用接口
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        // 设置支付成功的同步回调地址（GET）
        request.setReturnUrl(returnUrl);
        // 设置支付成功的异步回调地址（POST）
        request.setNotifyUrl(notifyUrl);
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        // 设置商户订单号
        model.setOutTradeNo(orderSn);
        // 设置订单总金额
        model.setTotalAmount(String.valueOf(order.getPayAmount()/1000));
        // 设置订单标题
        model.setSubject("测试订单");
        // 设置产品类型编码（电脑网站支付）
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        request.setBizModel(model);
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request,"POST");
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        httpServletResponse.getWriter().write(response.getBody());
    }

    /**
     * 支付成功后的同步回调
     * @param
     * @return
     */
    @GetMapping("/paySuccess")
    public void paySuccess(HttpServletResponse response){
        // 重定向到订单列表页面
        try {
            response.sendRedirect("https://www.baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /**
     * 支付成功后的异步回调--两个回调都可以验签，这里我在这里验签
     * @param
     * @return
     */
    @PostMapping("/payResult")
    public void payResult(@RequestParam Map<String,String> params){
        payService.payResult(params);
    }

}
