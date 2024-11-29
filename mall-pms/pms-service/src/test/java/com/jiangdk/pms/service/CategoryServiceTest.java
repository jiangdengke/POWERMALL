package com.jiangdk.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiangdk.pms.pojo.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;
    @Test
    void page(){
        Page<Category> page = categoryService.page(Page.of(1, 10));
        System.out.println("总记录数："+page.getTotal());
        List<Category> list = page.getRecords();
        list.forEach(item ->{
            System.out.println(item);
        });
    }
}