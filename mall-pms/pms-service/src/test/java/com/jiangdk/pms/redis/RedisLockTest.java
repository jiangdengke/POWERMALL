package com.jiangdk.pms.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author: JiangDk
 * @date: 2024/12/10 21:15
 * @description:
 */
@SpringBootTest
public class RedisLockTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    void test(){
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent("k1", "100");
        System.out.println(result);
    }
}
