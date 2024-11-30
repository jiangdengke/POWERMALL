package com.jiangdk.pms.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: JiangDk
 * @date: 2024/11/30 13:55
 * @description:
 */
@Data
public class SpuVO implements Serializable {
    // 主键
    private Long id;
    // 商品名称
    private String name;
    // 商品分类名称
    private String categoryName;
    // 商品分类ID
    private Long categoryId;
    // 价格
    private Integer price;
    // 销量
    private Integer sales;
    // 商品主图
    private String img;
    // 商品详情
    private String detail;
    // 商品状态  0:下架 1:上架
    private Integer status;
    // 商品图集
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> imgList;
    // 规格列表
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<?> specList;
    // 库存信息
    private List<SkuVO> skuList;
    // 总库存数量
    private Integer totalStock;
    // 计算商品库存总数量
    public Integer getTotalStock() {
        return skuList.stream()
                .map(sku->sku.getStock())
                .reduce(Integer::sum).get();
    }
}
