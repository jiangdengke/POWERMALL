package com.jiangdk.pms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiangdk.common.mybatis.page.PageQuery;
import com.jiangdk.common.mybatis.page.PageResult;
import com.jiangdk.common.result.Result;
import com.jiangdk.pms.pojo.entity.Category;
import com.jiangdk.pms.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端/测试
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    @GetMapping
    public Result test(){
        int i = 100/0;
        return Result.success();
    }
    @GetMapping("/page")
    public PageResult page(
             PageQuery pageQuery
    ){
        Page<Category> page = categoryServiceImpl.page(Page.of(pageQuery.getCurrent(), pageQuery.getSize()));
        return PageResult.success(page);
    }
}
