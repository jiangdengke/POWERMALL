package com.jiangdk.pms.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 商品表
 */
@Data
@TableName(value = "pms_spu",autoResultMap = true)
public class Spu {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 商品分类ID
     */
    @TableField(value = "category_id")
    private Long categoryId;

    /**
     * 价格
     */
    @TableField(value = "price")
    private Integer price;

    /**
     * 销量
     */
    @TableField(value = "sales")
    private Integer sales;

    /**
     * 商品主图
     */
    @TableField(value = "img")
    private String img;

    /**
     * 商品图集
     */
    @TableField(value = "img_list",typeHandler = JacksonTypeHandler.class)
    private List<String> imgList;

    /**
     * 商品详情
     */
    @TableField(value = "detail")
    private String detail;

    /**
     * 商品状态  0:下架 1:上架
     */
    @TableField(value = "`status`")
    private Byte status;

    /**
     * 商品规格
     */
    @TableField(value = "spec_list",typeHandler = JacksonTypeHandler.class)
    private List<?> specList;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;


}