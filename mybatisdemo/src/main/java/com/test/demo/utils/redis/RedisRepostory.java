package com.test.demo.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepostory {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public void add(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void add(String key, String value, Long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.MINUTES);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.opsForValue().getOperations().delete(key);
    }
}
