package com.jiangdk.pms.controller.app;

import cn.hutool.core.lang.tree.Tree;
import com.jiangdk.common.result.Result;
import com.jiangdk.pms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 应用端/商品
 */
@RestController
@RequestMapping("/api/pms")
public class AppCategoryController {
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
