package com.jiangdk.oms.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: JiangDk
 * @date: 2024/12/9 21:28
 * @description:
 */
@Data
public class OrderForm {
    // 订单编号
    private String orderSn;
    // 订单描述
    private String remark;

    // 付款类型 1.微信 2.支付宝
    @NotNull(message = "付款类型不能为空")
    private Integer payType;

    // 订单商品总金额
    @NotNull(message = "订单商品总金额不能为空")
    private Integer totalAmount;

    // 应付款金额
    @NotNull(message = "应付款金额不能为空")
    private Integer payAmount;

    // 购买商品明细
    private List<OrderItemForm> orderItems;
}
