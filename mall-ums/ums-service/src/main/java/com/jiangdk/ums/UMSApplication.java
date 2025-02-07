package com.jiangdk.ums;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: JiangDk
 * @date: 2024/12/12 15:14
 * @description:
 */

@SpringBootApplication
@MapperScan("com.jiangdk.ums.mapper")
public class UMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(UMSApplication.class,args);
    }
}
