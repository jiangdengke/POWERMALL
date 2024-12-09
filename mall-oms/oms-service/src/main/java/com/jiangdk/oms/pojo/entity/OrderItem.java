package com.jiangdk.oms.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
 * 订单商品信息
 */
@TableName(value = "oms_order_item")
public class OrderItem {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单id
     */
    @TableField(value = "order_id")
    private Long orderId;

    /**
     * 商品ID
     */
    @TableField(value = "spu_id")
    private Long spuId;

    /**
     * 商品库存id
     */
    @TableField(value = "sku_id")
    private Long skuId;

    /**
     * 商品名称
     */
    @TableField(value = "spu_name")
    private String spuName;

    /**
     * 规格名称
     */
    @TableField(value = "sku_name")
    private String skuName;

    /**
     * 商品图片
     */
    @TableField(value = "img")
    private String img;

    /**
     * 商品单价
     */
    @TableField(value = "price")
    private Integer price;

    /**
     * 商品购买数量
     */
    @TableField(value = "`count`")
    private Integer count;

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

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取商品ID
     *
     * @return spu_id - 商品ID
     */
    public Long getSpuId() {
        return spuId;
    }

    /**
     * 设置商品ID
     *
     * @param spuId 商品ID
     */
    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    /**
     * 获取商品库存id
     *
     * @return sku_id - 商品库存id
     */
    public Long getSkuId() {
        return skuId;
    }

    /**
     * 设置商品库存id
     *
     * @param skuId 商品库存id
     */
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    /**
     * 获取商品名称
     *
     * @return spu_name - 商品名称
     */
    public String getSpuName() {
        return spuName;
    }

    /**
     * 设置商品名称
     *
     * @param spuName 商品名称
     */
    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    /**
     * 获取规格名称
     *
     * @return sku_name - 规格名称
     */
    public String getSkuName() {
        return skuName;
    }

    /**
     * 设置规格名称
     *
     * @param skuName 规格名称
     */
    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    /**
     * 获取商品图片
     *
     * @return img - 商品图片
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置商品图片
     *
     * @param img 商品图片
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 获取商品单价
     *
     * @return price - 商品单价
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 设置商品单价
     *
     * @param price 商品单价
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * 获取商品购买数量
     *
     * @return count - 商品购买数量
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置商品购买数量
     *
     * @param count 商品购买数量
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}