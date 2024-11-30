package com.jiangdk.pms.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: JiangDk
 * @date: 2024/11/29 19:45
 * @description: 接收表单提交的商品分类的数据
 */
@Data
public class CategoryForm {
    // 主键
    private Long id;
    // 上级分类ID
    @NotNull(message = "上级分类ID不能为空")
    private Long parentId;
    // 分类名称
    @NotBlank(message = "商品分类名称不能为空")
    private String name;
    // 图标
    @NotBlank(message = "商品分类图标不能为空")
    private String icon;
    // 排序
    @NotNull(message = "展示顺序不能为空")
    private Double weight;
}
