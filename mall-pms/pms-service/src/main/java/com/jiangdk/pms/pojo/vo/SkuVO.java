package com.jiangdk.pms.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.util.List;

/**
 * @author: JiangDk
 * @date: 2024/11/30 14:37
 * @description: 商品库存规格对象
 */
@Data
public class SkuVO {
    // sku_id
    private Long id;
    // spu_id
    private Long spuId;
    // 规格组合的名称
    private String name;
    // 价格
    private Integer price;
    // 库存
    private Integer stock;
    // 规格值的组合
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<?> specValueList;
    // 商品状态  0:下架 1:上架
    private Integer status;
}
