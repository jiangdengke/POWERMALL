package com.jiangdk.common.redis.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

/**
 * Redis 缓存配置
 */
@Configuration
// 启用缓存
@EnableCaching
public class RedisCacheConfig {

    /**
     * 配置缓存管理器
     * <bean class="xxx.RedisConnectionFactory" name="factory"/>
     *
     * @return <bean class="xxxx.RedisCacheManager" ref="factory"/>
     */
    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory factory) {
        // 获取默认配置
        RedisCacheConfiguration config =
                RedisCacheConfiguration.defaultCacheConfig()
                        //key的序列化 string
                        .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()))
                        //value的序列化 json
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()))
                        // 设置key的分隔符
                        .computePrefixWith(cacheName -> cacheName + ":")
                        // 设置key的有效期
                        .entryTtl(Duration.ofSeconds(30));
        // 禁止缓存空对象
        //.disableCachingNullValues();

        return RedisCacheManager
                .builder(factory)
                .cacheDefaults(config)
                .build();
    }
}