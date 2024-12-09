package com.jiangdk.oms.service;

import com.jiangdk.oms.pojo.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangdk.oms.pojo.form.OrderForm;
import com.jiangdk.oms.pojo.vo.OrderVO;

public interface OrderService extends IService<Order>{

    /**
     * 确认订单
     * 从商品详情页面下单
     * @param skuId
     * @return
     */
    OrderVO orderConfirm(Long skuId, Integer count);

    /**
     * 确认订单
     * 从购物车下单
     * @return
     */
    OrderVO orderConfirm();
    /**
     * 提交订单
     * @param orderForm
     * @return
     */
    void orderSubmit(OrderForm orderForm);
}
