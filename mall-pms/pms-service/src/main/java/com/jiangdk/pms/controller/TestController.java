package com.jiangdk.pms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiangdk.common.mybatis.page.PageQuery;
import com.jiangdk.common.mybatis.page.PageResult;
import com.jiangdk.common.result.Result;
import com.jiangdk.pms.pojo.entity.Category;
import com.jiangdk.pms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 管理端/测试
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public Result test(){
        int i = 100/0;
        return Result.success();
    }
    @GetMapping("/page")
    public PageResult page(
             PageQuery pageQuery
    ){
        Page<Category> page = categoryService.page(Page.of(pageQuery.getCurrent(), pageQuery.getSize()));
        return PageResult.success(page);
    }
}
