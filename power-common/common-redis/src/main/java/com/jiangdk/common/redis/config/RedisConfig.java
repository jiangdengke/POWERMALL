package com.jiangdk.common.redis.config;

import io.lettuce.core.ReadFrom;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {
    /**
     * 序列化设置
     * @param
     * @return
     */
    @Bean
    public RedisTemplate<String,?> redisTemplate(
                                    RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String,?> redisTemplate = new RedisTemplate<>();
        // 配置redis数据库的连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置key序列化
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        // 设置value序列化
        redisTemplate.setValueSerializer(RedisSerializer.json());
        redisTemplate.setHashValueSerializer(RedisSerializer.json());
        return redisTemplate;
    }
    /**
     * 配置读写分离
     */
    @Bean
    public LettuceClientConfigurationBuilderCustomizer clientConfigurationBuilderCustomizer(){
        return clientConfigurationBuilder -> clientConfigurationBuilder.readFrom(ReadFrom.REPLICA_PREFERRED);
    }
}