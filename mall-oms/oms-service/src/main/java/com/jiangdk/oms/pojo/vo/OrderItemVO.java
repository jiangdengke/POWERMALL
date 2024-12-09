package com.jiangdk.oms.pojo.vo;

import lombok.*;

/**
 * @author: JiangDk
 * @date: 2024/12/9 16:29
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemVO {
    // 商品ID
    private Long spuId;
    // 商品规格ID
    private Long skuId;
    // 商品名称
    private String spuName;
    // 商品规格名称
    private String skuName;
    // 商品图片
    private String img;
    // 商品单价
    private Integer price;
    // 商品购买数量
    private Integer count;
    // 商品小计价格
    private Integer totalPrice;

    public Integer getTotalPrice() {
        return price * count;
    }
}
