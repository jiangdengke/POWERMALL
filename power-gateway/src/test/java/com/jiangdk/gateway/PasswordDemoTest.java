package com.jiangdk.gateway;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.secure.SaSecureUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: JiangDk
 * @date: 2024/12/12 16:04
 * @description:
 */
@SpringBootTest
public class PasswordDemoTest {
    @Test
    void test(){

        // 使用方法
        String pw_hash = BCrypt.hashpw("123456", BCrypt.gensalt());

        System.out.println(pw_hash);
        // 使用checkpw方法检查被加密的字符串是否与原始字符串匹配：
        BCrypt.checkpw("123456", pw_hash);


    }
}
