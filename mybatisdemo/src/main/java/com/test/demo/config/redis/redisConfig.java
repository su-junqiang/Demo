package com.test.demo.config.redis;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;



@Configuration
@EnableCaching//开启缓存
public class redisConfig {
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory)
    {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(factory);
        return stringRedisTemplate;
    }
}