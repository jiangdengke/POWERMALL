package com.jiangdk.pms.pojo.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: JiangDk
 * @date: 2024/11/30 16:11
 * @description:
 */
@Data
public class SkuForm {
    private Long id;
    /**
     * SPU ID
     */
    private Long spuId;
    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private Integer price;

    /**
     * 库存数量
     */
    private Integer stock;

    /**
     * 商品规格值
     */
    private List<?> specValueList;

    /**
     * 状态：0.下架 1.上架
     */
    private Integer status;
}
