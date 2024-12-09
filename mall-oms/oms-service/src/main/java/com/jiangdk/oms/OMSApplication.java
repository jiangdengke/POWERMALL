package com.jiangdk.oms;

import com.jiangdk.pms.feign.SkuFeignClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = {SkuFeignClient.class})
@MapperScan("com.jiangdk.oms.mapper")
public class OMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(OMSApplication.class, args);
    }
}