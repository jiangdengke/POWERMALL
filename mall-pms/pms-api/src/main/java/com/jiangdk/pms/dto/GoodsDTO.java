package com.jiangdk.pms.dto;

import lombok.Data;

@Data
public class GoodsDTO {

    private Long id; //文档ID

    private Long categoryId;//分类ID

    private String name;  //商品名称

    private Integer price;//商品价格

    private Integer sales;//商品销量

    private String img;//图片地址

}