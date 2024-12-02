package com.jiangdk.cms.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
 * 网站内容表
 */
@TableName(value = "cms_content")
public class CmsContent {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分组名称
     */
    @TableField(value = "group_name")
    private String groupName;

    /**
     * 分组编码
     */
    @TableField(value = "group_code")
    private String groupCode;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 内容编码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 内容排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 图片
     */
    @TableField(value = "img")
    private String img;

    /**
     * 点击跳转地址
     */
    @TableField(value = "redirect_url")
    private String redirectUrl;

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
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取分组名称
     *
     * @return group_name - 分组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置分组名称
     *
     * @param groupName 分组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取分组编码
     *
     * @return group_code - 分组编码
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * 设置分组编码
     *
     * @param groupCode 分组编码
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取内容编码
     *
     * @return code - 内容编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置内容编码
     *
     * @param code 内容编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取内容排序
     *
     * @return sort - 内容排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置内容排序
     *
     * @param sort 内容排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取图片
     *
     * @return img - 图片
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置图片
     *
     * @param img 图片
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 获取点击跳转地址
     *
     * @return redirect_url - 点击跳转地址
     */
    public String getRedirectUrl() {
        return redirectUrl;
    }

    /**
     * 设置点击跳转地址
     *
     * @param redirectUrl 点击跳转地址
     */
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
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