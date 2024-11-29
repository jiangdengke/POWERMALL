package com.jiangdk.pms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jiangdk.pms.mapper")
public class PMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(PMSApplication.class, args);
    }
}