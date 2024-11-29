package com.jiangdk.pms.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
 * 商品分类
 */
@TableName(value = "pms_category")
public class Category {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父ID
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 分类名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 分类层级 1.一级分类 2.二级分类 3.三级分类
     */
    @TableField(value = "`level`")
    private Integer level;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 排序
     */
    @TableField(value = "weight")
    private Double weight;

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
     * 获取父ID
     *
     * @return parent_id - 父ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父ID
     *
     * @param parentId 父ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取分类名称
     *
     * @return name - 分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分类名称
     *
     * @param name 分类名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取分类层级 1.一级分类 2.二级分类 3.三级分类
     *
     * @return level - 分类层级 1.一级分类 2.二级分类 3.三级分类
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置分类层级 1.一级分类 2.二级分类 3.三级分类
     *
     * @param level 分类层级 1.一级分类 2.二级分类 3.三级分类
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取排序
     *
     * @return weight - 排序
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * 设置排序
     *
     * @param weight 排序
     */
    public void setWeight(Double weight) {
        this.weight = weight;
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