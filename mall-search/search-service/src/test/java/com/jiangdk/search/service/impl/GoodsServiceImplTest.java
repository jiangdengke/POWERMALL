package com.jiangdk.search.service.impl;

import com.jiangdk.search.document.GoodsDoc;
import com.jiangdk.search.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GoodsServiceImplTest {
    @Autowired
    private GoodsService goodsService;
    @Test
    void save() {

        GoodsDoc goods1 = new GoodsDoc();
        goods1.setId(101L);
        goods1.setName("HUAWEI P150 原色双影像单元 搭载HarmonyOS 2 " +
                "万象双环设计 支持66W超级快充 8GB+256GB雪域白 华为手机");
        goods1.setPrice(4558);
        goods1.setImg("https://mall.imcode.top/product/01.jpg");
        goods1.setSales(80);
        goods1.setCategoryId(1L);
        goodsService.save(goods1);

        GoodsDoc goods2 = new GoodsDoc();
        goods2.setId(102L);
        goods2.setName("小米13 Pro 开启预约！12月1日 xiaomi 13系列发布会 年度全能旗舰 小米手机");
        goods2.setPrice(4299);
        goods2.setImg("https://mall.imcode.top/product/02.jpg");
        goods2.setSales(100);
        goods2.setCategoryId(2L);
        goodsService.save(goods2);
    }
}