package com.ghoject.cloud.helloworld.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDao {

    @Autowired
    private RedisTemplate redisTemplate;

    public void putValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public <T> T getValue(String key) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }
}
