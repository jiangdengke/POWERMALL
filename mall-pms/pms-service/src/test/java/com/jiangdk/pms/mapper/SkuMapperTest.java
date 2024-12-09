package com.jiangdk.pms.mapper;

import com.jiangdk.pms.pojo.vo.CartItemVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SkuMapperTest {
    @Autowired
    private SkuMapper skuMapper;
    @Test
    void testDemo(){
        List<Long> skuIds = new ArrayList<>();
        skuIds.add(1L);
        skuIds.add(2L);
        List<CartItemVO> cartItemVOS = skuMapper.selectCartItemByIds(skuIds);
        cartItemVOS.stream().forEach(cartItemVO -> System.out.println(cartItemVO));
    }
}