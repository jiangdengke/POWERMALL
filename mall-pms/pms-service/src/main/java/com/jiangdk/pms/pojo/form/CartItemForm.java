package com.jiangdk.pms.pojo.form;

import lombok.Data;

/**
 * @author: JiangDk
 * @date: 2024/12/7 19:48
 * @description: 购物车中的商品
 */
@Data
public class CartItemForm {

    // 商品skuId
    private Long skuId;


    // 商品在购物车的数量
    private Integer count;

    // 商品在购物车中的选中状态
    private Boolean checked;
}
