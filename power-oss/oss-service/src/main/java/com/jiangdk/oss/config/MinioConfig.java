package com.jiangdk.oss.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: JiangDk
 * @date: 2024/12/1 16:49
 * @description:
 */
@Configuration
public class MinioConfig {
    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;
    @Bean
    public MinioClient minioClient(){
        MinioClient minioClient = MinioClient
                .builder().credentials(accessKey, secretKey)
                .endpoint(endpoint)
                .build();
        return minioClient;
    }
}
