package com.jiangdk.oms.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
 * 订单配送信息
 */
@TableName(value = "oms_order_delivery")
public class OrderDelivery {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单ID
     */
    @TableField(value = "order_id")
    private Long orderId;

    /**
     * 收货人
     */
    @TableField(value = "receiver_name")
    private String receiverName;

    /**
     * 手机号
     */
    @TableField(value = "receiver_mobile")
    private String receiverMobile;

    /**
     * 省份
     */
    @TableField(value = "receiver_province")
    private String receiverProvince;

    /**
     * 城市
     */
    @TableField(value = "receiver_city")
    private String receiverCity;

    /**
     * 区/县
     */
    @TableField(value = "receiver_area")
    private String receiverArea;

    /**
     * 详细收货地址
     */
    @TableField(value = "receiver_address")
    private String receiverAddress;

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
     * 获取订单ID
     *
     * @return order_id - 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单ID
     *
     * @param orderId 订单ID
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取收货人
     *
     * @return receiver_name - 收货人
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * 设置收货人
     *
     * @param receiverName 收货人
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    /**
     * 获取手机号
     *
     * @return receiver_mobile - 手机号
     */
    public String getReceiverMobile() {
        return receiverMobile;
    }

    /**
     * 设置手机号
     *
     * @param receiverMobile 手机号
     */
    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    /**
     * 获取省份
     *
     * @return receiver_province - 省份
     */
    public String getReceiverProvince() {
        return receiverProvince;
    }

    /**
     * 设置省份
     *
     * @param receiverProvince 省份
     */
    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    /**
     * 获取城市
     *
     * @return receiver_city - 城市
     */
    public String getReceiverCity() {
        return receiverCity;
    }

    /**
     * 设置城市
     *
     * @param receiverCity 城市
     */
    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    /**
     * 获取区/县
     *
     * @return receiver_area - 区/县
     */
    public String getReceiverArea() {
        return receiverArea;
    }

    /**
     * 设置区/县
     *
     * @param receiverArea 区/县
     */
    public void setReceiverArea(String receiverArea) {
        this.receiverArea = receiverArea;
    }

    /**
     * 获取详细收货地址
     *
     * @return receiver_address - 详细收货地址
     */
    public String getReceiverAddress() {
        return receiverAddress;
    }

    /**
     * 设置详细收货地址
     *
     * @param receiverAddress 详细收货地址
     */
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
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