package com.jiangdk.pms.controller.admin;

import cn.hutool.core.lang.tree.Tree;
import com.jiangdk.common.result.Result;
import com.jiangdk.pms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理端/商品分类管理
 */
@RestController
@RequestMapping("/api-admin/pms/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    /**
     * 商品分类列表
     * @return
     */
    @GetMapping
    public Result<List<Tree<Long>>> tree() {
        return Result.success(categoryService.tree());
    }
}
