package com.macro.auth.common.util.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @auther Macro
 * @date 2019-07-03 11:13
 * @description redis缓存
 */
@Component
@Slf4j
public class RedisUtil implements RedisService{

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void set(Object key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public void set(Object key, Object value, long expTime, TimeUnit timeUnit) {

        redisTemplate.opsForValue().set(key,value,expTime,timeUnit);
    }

    @Override
    public void set(Object key, Object value, long expTime) {
        redisTemplate.opsForValue().set(key,value,expTime,TimeUnit.SECONDS);
    }

    @Override
    public <T> T get(Object key) {
        return key == null ? null : (T)redisTemplate.opsForValue().get(key);
    }


    @Override
    public void del(Object key) {
        redisTemplate.delete(key);
    }




}
