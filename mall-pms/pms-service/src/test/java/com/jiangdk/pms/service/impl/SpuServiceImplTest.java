package com.jiangdk.pms.service.impl;

import com.jiangdk.pms.service.SpuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SpuServiceImplTest {

    @Autowired
    private SpuService spuService;
    @Test
    void getSpuById(){
        System.out.println(spuService.getSpuById(1L));

    }
}