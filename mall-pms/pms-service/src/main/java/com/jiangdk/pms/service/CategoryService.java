package com.jiangdk.pms.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangdk.pms.pojo.entity.Category;
import com.jiangdk.pms.pojo.form.CategoryForm;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author: JiangDk
 * @date: 2024/11/29 19:49
 * @description:
 */

public interface CategoryService extends IService<Category> {
    /**
     * 获取商品分类树
     */
    // 缓存商品分类
    @Cacheable(cacheNames = "category",key = "'tree'")
    List<Tree<Long>> tree();

    /**
     * 新增商品分类
     * @param categoryForm
     */
    @CacheEvict(cacheNames = "category", key = "'tree'")
    void addCategory(CategoryForm categoryForm);
    /**
     * 更新商品分类
     */
    @CacheEvict(cacheNames = "category", key = "'tree'")
    void updateCategoryById(CategoryForm categoryForm);
    /**
     * 删除商品分类
     */
    @CacheEvict(cacheNames = "category", key = "'tree'")
    void deleteCategoryById(Long categoryId);
}
