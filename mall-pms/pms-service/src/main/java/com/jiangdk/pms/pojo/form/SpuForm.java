package com.jiangdk.pms.pojo.form;

import com.jiangdk.pms.pojo.entity.Sku;
import lombok.Data;

import java.util.List;

/**
 * @author: JiangDk
 * @date: 2024/11/30 16:10
 * @description:
 */
@Data
public class SpuForm {
    // 主键
    private Long id;
    // 商品名称
    private String name;
    // 商品分类ID
    private Long categoryId;
    // 价格
    private Integer price;
    // 销量
    private Integer sales;
    // 商品主图
    private String img;
    // 商品图集
    private List<String> imgList;
    // 商品详情
    private String detail;
    // 商品状态  0:下架 1:上架
    private Integer status;
    // 商品规格
    private List<?> specList;
    // 商品库存
    private List<SkuForm> skuList;
}
