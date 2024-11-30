package com.jiangdk.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiangdk.pms.pojo.entity.Category;
import com.jiangdk.pms.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    @Test
    void page(){
        Page<Category> page = categoryServiceImpl.page(Page.of(1, 10));
        System.out.println("总记录数："+page.getTotal());
        List<Category> list = page.getRecords();
        list.forEach(item ->{
            System.out.println(item);
        });
    }
}