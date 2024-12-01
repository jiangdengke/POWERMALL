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
 * 商品库存表
 */
@Data
@TableName(value = "pms_sku",autoResultMap = true)
public class Sku {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * SPU ID
     */
    @TableField(value = "spu_id")
    private Long spuId;

    /**
     * 商品名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 商品价格
     */
    @TableField(value = "price")
    private Integer price;

    /**
     * 库存数量
     */
    @TableField(value = "stock")
    private Integer stock;

    /**
     * 商品规格值
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<?> specValueList;

    /**
     * 状态：0.下架 1.上架
     */
    @TableField(value = "`status`")
    private Integer status;

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