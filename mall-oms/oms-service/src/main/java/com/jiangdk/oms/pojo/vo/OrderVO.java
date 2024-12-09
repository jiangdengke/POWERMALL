package com.jiangdk.oms.pojo.vo;

import lombok.*;

import java.util.List;

/**
 * @author: JiangDk
 * @date: 2024/12/9 16:32
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {
    // 订单编号
    private String orderSn;
    // 订单商品总金额
    private Integer totalAmount;
    // 应付款金额
    private Integer payAmount;
    // 订单明细
    private List<OrderItemVO> orderItems;
    // 运费
    // 优惠活动
    // 可用的优惠券列表
    // 获取订单商品的总金额(商品单价*数量求和)
    public Integer getTotalAmount() {
        return orderItems.stream().map(item->item.getTotalPrice()).reduce(Integer::sum).get();
    }

    // 计算应付款金额
    public Integer getPayAmount() {
        return this.getTotalAmount();
    }
}
