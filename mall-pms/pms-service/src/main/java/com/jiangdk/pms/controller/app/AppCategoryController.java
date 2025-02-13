package com.jiangdk.pms.controller.app;

import com.jiangdk.common.result.Result;
import com.jiangdk.pms.pojo.vo.CategoryVO;
import com.jiangdk.pms.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 应用端/商品分类
 */
@RestController
@RequestMapping("/api/pms")
public class AppCategoryController {
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    /**
     * 商品分类列表
     * @return
     */
//    @GetMapping
//    public Result<List<Tree<Long>>> tree() {
//        return Result.success(categoryServiceImpl.tree());
//    }
    @GetMapping
    public Result<List<CategoryVO>> list() {
        List<CategoryVO> categoryTree = categoryServiceImpl.getCategoryList();
        return Result.success(categoryTree);
    }
}
