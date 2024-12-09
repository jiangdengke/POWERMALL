package com.jiangdk.oms.pojo.vo;

import lombok.Data;

@Data
public class CartItemVO {
    // 商品spuId
    private Long spuId;
    // 商品skuId
    private Long skuId;
    // 商品spuName
    private String spuName;
    // 商品skuName
    private String skuName;
    // 商品最新价格(sku)
    private Integer price;
    // 商品加入购物车时的价格
    private Integer originalPrice;
    // 商品状态（sku）
    private Integer status;
    // 商品库存(sku)
    private Integer stock;
    // 商品在购物车的数量
    private Integer count;
    // 商品在购物车中的选中状态
    private Boolean checked;
    //
    private Double score;
}
