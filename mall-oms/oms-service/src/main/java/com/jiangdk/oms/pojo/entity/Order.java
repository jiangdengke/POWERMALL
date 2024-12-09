package com.jiangdk.oms.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
 * 订单
 */
@TableName(value = "oms_order")
public class Order {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单编号
     */
    @TableField(value = "order_sn")
    private String orderSn;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 订单总金额
     */
    @TableField(value = "total_amount")
    private Integer totalAmount;

    /**
     * 1-待付款;2-待发货;3-已发货;4-已完成;;5-已取消;
     */
    @TableField(value = "`status`")
    private Byte status;

    /**
     * 订单备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 订单创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 付款方式：1-微信，2-支付宝
     */
    @TableField(value = "pay_type")
    private Integer payType;

    /**
     * 应付金额
     */
    @TableField(value = "pay_amount")
    private Integer payAmount;

    /**
     * 付款时间
     */
    @TableField(value = "pay_time")
    private Date payTime;

    /**
     * 发货时间
     */
    @TableField(value = "delivery_time")
    private Date deliveryTime;

    /**
     * 确认收货时间
     */
    @TableField(value = "receive_time")
    private Date receiveTime;

    /**
     * 订单取消时间
     */
    @TableField(value = "cancel_time")
    private Date cancelTime;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取订单编号
     *
     * @return order_sn - 订单编号
     */
    public String getOrderSn() {
        return orderSn;
    }

    /**
     * 设置订单编号
     *
     * @param orderSn 订单编号
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取订单总金额
     *
     * @return total_amount - 订单总金额
     */
    public Integer getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置订单总金额
     *
     * @param totalAmount 订单总金额
     */
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取1-待付款;2-待发货;3-已发货;4-已完成;;5-已取消;
     *
     * @return status - 1-待付款;2-待发货;3-已发货;4-已完成;;5-已取消;
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置1-待付款;2-待发货;3-已发货;4-已完成;;5-已取消;
     *
     * @param status 1-待付款;2-待发货;3-已发货;4-已完成;;5-已取消;
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取订单备注
     *
     * @return remark - 订单备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置订单备注
     *
     * @param remark 订单备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取订单创建时间
     *
     * @return create_time - 订单创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置订单创建时间
     *
     * @param createTime 订单创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取付款方式：1-微信，2-支付宝
     *
     * @return pay_type - 付款方式：1-微信，2-支付宝
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * 设置付款方式：1-微信，2-支付宝
     *
     * @param payType 付款方式：1-微信，2-支付宝
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * 获取应付金额
     *
     * @return pay_amount - 应付金额
     */
    public Integer getPayAmount() {
        return payAmount;
    }

    /**
     * 设置应付金额
     *
     * @param payAmount 应付金额
     */
    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 获取付款时间
     *
     * @return pay_time - 付款时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置付款时间
     *
     * @param payTime 付款时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取发货时间
     *
     * @return delivery_time - 发货时间
     */
    public Date getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * 设置发货时间
     *
     * @param deliveryTime 发货时间
     */
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * 获取确认收货时间
     *
     * @return receive_time - 确认收货时间
     */
    public Date getReceiveTime() {
        return receiveTime;
    }

    /**
     * 设置确认收货时间
     *
     * @param receiveTime 确认收货时间
     */
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    /**
     * 获取订单取消时间
     *
     * @return cancel_time - 订单取消时间
     */
    public Date getCancelTime() {
        return cancelTime;
    }

    /**
     * 设置订单取消时间
     *
     * @param cancelTime 订单取消时间
     */
    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }
}