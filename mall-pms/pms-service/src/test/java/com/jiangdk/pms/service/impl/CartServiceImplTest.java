package com.jiangdk.pms.service.impl;

import com.jiangdk.pms.pojo.vo.CartItemVO;
import com.jiangdk.pms.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CartServiceImplTest {
    @Autowired
    private CartService cartService;
    @Test
    void add() {
        cartService.add(1L,5);
        cartService.add(2L,5);
        cartService.add(3L,5);
        cartService.add(4L,5);
    }

    @Test
    void updateCount() {
        cartService.updateCount(1002L,20);
    }

    @Test
    void getAll() {
        List<CartItemVO> all = cartService.getAll();
        all.forEach(item-> System.out.println(item));
    }

    @Test
    void checked() {
        cartService.checked(1002L,false);
    }

    @Test
    void checkedAll() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteAll() {
    }
}