package com.jiangdk.pms.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author: JiangDk
 * @date: 2024/11/30 13:55
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
        // 1. 检查 skuList 是否为空
        if (skuList == null || skuList.isEmpty()) {
            return 0;
        }

        // 2. 使用 Optional 安全处理
        return skuList.stream()
                .map(SkuVO::getStock)           // 获取库存数量
                .filter(stock -> stock != null)  // 过滤掉 null 值
                .reduce(0, Integer::sum);        // 使用初始值 0 进行求和
    }
}
