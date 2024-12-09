package com.jiangdk.oms.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: JiangDk
 * @date: 2024/12/9 21:23
 * @description:
 */
@Data
public class OrderItemForm {
    // 商品规格ID
    @NotNull(message = "商品规格ID不能为空")
    private Long skuId;
    // 商品单价
    @NotNull(message = "商品单价不能为空")
    private Integer price;
    // 商品购买数量
    @NotNull(message = "商品购买数量不能为空")
    private Integer count;
}
