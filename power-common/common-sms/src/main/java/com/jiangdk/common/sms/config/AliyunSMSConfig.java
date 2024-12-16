package com.jiangdk.common.sms.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliyunSMSConfig {
    @Value("${aliyun.sms.regionId}")
    private String regionId;
    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.sms.secret}")
    private String secret;
    @Bean
    IAcsClient iAcsClient(){
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        return new DefaultAcsClient(profile);
    }
}
