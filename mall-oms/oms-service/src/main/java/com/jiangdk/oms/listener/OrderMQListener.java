package com.jiangdk.oms.listener;

import com.jiangdk.oms.service.OrderService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: JiangDk
 * @date: 2024/12/10 15:07
 * @description:
 */
@Component
public class OrderMQListener {
    @Autowired
    private OrderService orderService;

    /**
     * 监听订单的取消
     * @param orderId
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "oms.order.cancel"),
            exchange = @Exchange(name = "oms.order", delayed = "true"),
            key = {"order.cancel"}
    ))
    public void cancelOrder(String orderId){
        orderService.orderCancel(orderId);
    }
}
