package com.jiangdk.search.controller;

import com.jiangdk.search.document.GoodsDoc;
import com.jiangdk.search.pojo.page.PageResult;
import com.jiangdk.search.pojo.query.GoodsQuery;
import com.jiangdk.search.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用端/商品
 */
@RestController
@RequestMapping("/api/search/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 商品【搜索】
     * @param param
     * @return
     */
    @GetMapping
    public PageResult<GoodsDoc> searchProduct(GoodsQuery param){
        return goodsService.search(param);
    }
}
