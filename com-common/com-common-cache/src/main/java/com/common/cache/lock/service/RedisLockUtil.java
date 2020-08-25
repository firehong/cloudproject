package com.common.cache.lock.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @auther Macro
 * @date 2019-07-03 11:13
 * @description redis缓存
 */
@Component
@Slf4j
public class RedisLockUtil implements RedisLockService {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public boolean getLock(String key, Long waitingTime, Long timeout, TimeUnit unit) {
        RLock lock = redissonClient.getLock(key);
        try {
            return lock.tryLock(waitingTime, timeout, unit);
        } catch (Exception e) {
            log.error("[ redisLock ] redisson分布式锁加锁异常：{}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public void unlock(String lockKey) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            if(lock.isLocked()){
                if(lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }
        }catch (Exception e){
            log.error("[ redisLock ] redisson分布式锁释放锁异常：{}", e.getMessage(), e);
        }
    }



}
