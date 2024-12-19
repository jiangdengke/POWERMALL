package com.jiangdk.pms.controller.admin;

import com.jiangdk.common.result.Result;
import com.jiangdk.pms.pojo.form.CategoryForm;
import com.jiangdk.pms.pojo.vo.CategoryVO;
import com.jiangdk.pms.service.CategoryService;
import com.jiangdk.pms.service.SkuService;
import com.jiangdk.pms.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理端/商品分类管理
 */
@RestController
@RequestMapping("/api-admin/pms/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    @Autowired
    private SkuService skuService;
    @GetMapping
    public Result<List<CategoryVO>> list() {
        List<CategoryVO> categoryTree = categoryServiceImpl.getCategoryList();
        return Result.success(categoryTree);
    }

    /**
     * 商品分类列表
     * @return
     */
//    @GetMapping
//    public Result<List<Tree<Long>>> tree() {
//        return Result.success(categoryService.tree());
//    }

    /**
     * 新增商品分类
     * @param categoryForm
     * @return
     */
    @PostMapping
    public Result add(
            @RequestBody @Validated CategoryForm categoryForm){
        categoryService.addCategory(categoryForm);
        return Result.success();
    }

    /**
     * 更新商品分类
     * @param categoryId
     * @param categoryForm
     * @return
     */
    @PutMapping("/{categoryId}")
    public Result updateById(
            @PathVariable("categoryId") Long categoryId,
            @Validated @RequestBody CategoryForm categoryForm
    ){
        categoryForm.setId(categoryId);
        categoryService.updateCategoryById(categoryForm);
        return Result.success();
    }

    /**
     * 删除商品分类
     * @param categoryId
     * @return
     */
    @DeleteMapping("/{categoryId}")
    public Result deleteById(
            @PathVariable("categoryId") Long categoryId
    ){
        categoryService.deleteCategoryById(categoryId);
        return Result.success();
    }
}
