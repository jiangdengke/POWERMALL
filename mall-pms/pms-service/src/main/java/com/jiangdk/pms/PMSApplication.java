package com.jiangdk.pms;

import com.jiangdk.oss.feign.MinioFeignClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.jiangdk.pms.mapper")
@EnableFeignClients(basePackageClasses = {MinioFeignClient.class})
public class PMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(PMSApplication.class, args);
    }
}