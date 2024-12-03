package com.jiangdk.search.pojo.query;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: JiangDk
 * @date: 2024/12/3 19:14
 * @description:
 */
@Data
public class GoodsQuery {
    // 商品名称
    private String name;
    // 商品分类id
    private Long categoryId;
    // 价格区间开始
    private Double gtePrice;
    // 价格区间结束
    private Double ltePrice;
    // 排序字段
    private String orderBy;
    // 排序方向
    private String direction;
    // 当前页码
    private Integer current;
    // 一页显示多少条
    private Integer size;
}
