package com.macro.user.common.util.redis;

import java.util.concurrent.TimeUnit;

/**
 * @auther Macro
 * @date 2019-07-03 10:48
 * @description redis缓存接口
 */
public interface RedisService {

    /**
     * 设置缓存 k-v
     */
    void set(Object key, Object value);

    /**
     * 设置缓存 k-v 以及过期时间
     */
    void set(Object key, Object value, long expTime, TimeUnit timeUnit);

    /**
     * 设置缓存 k-v 以及过期时间 默认单位s
     */
    void set(Object key, Object value, long expTime);

    /**
     * 获取缓存对象
     */
    <T> T get(Object key, Class<T> type);

    /**
     * 获取缓存
     */
    Object get(Object key);

    /**
     * 删除缓存
     */
    void del(Object key);

    /**
     * 获取分布式锁
     * @param key 锁key
     * @param waitingTime 加锁等待时长
     * @param timeout 锁超时时长
     * @param unit 时间类型
     */
    boolean getLock(String key, Long waitingTime, Long timeout, TimeUnit unit);

    /**
     * 释放分布式锁
     * @param lockKey 锁key
     */
    void unlock(String lockKey);


}
