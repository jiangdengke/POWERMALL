package com.jiangdk.pms.dto;

import lombok.*;

import java.util.List;

/**
 * @author: JiangDk
 * @date: 2024/12/10 17:06
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
    // 订单编号
    private String orderSn;

    // 商品库存集合
    private List<LockedSku> lockedSkus;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LockedSku {
        // 商品库存ID
        private Long skuId;
        // 购买数量
        private Integer count;
    }
}
