package com.jiangdk.pms.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkuDTO {

    // 商品ID
    private Long spuId;

    // 商品库存ID
    private Long skuId;

    // 商品名称
    private String spuName;
    
    // 商品库存名称
    private String skuName;
    
    // 商品图片
    private String img;
    
    // 商品价格
    private Integer price;

    // 商品库存数量
    private Integer stock;
}