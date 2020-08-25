package com.common.cache.lock.service;

import java.util.concurrent.TimeUnit;

/**
 * @auther Macro
 * @date 2019-07-03 10:48
 * @description redis缓存接口
 */
public interface RedisLockService {

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
