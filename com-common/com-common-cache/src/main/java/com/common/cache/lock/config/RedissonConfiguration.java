package com.common.cache.lock.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @Author Macro
 * @Date 2020/6/29 14:31
 * @Description  redisson 配置
 */
@Configuration
@Slf4j
public class RedissonConfiguration {

    /**
     * redisson客户端
     * @return
     * @throws IOException
     */
    @Bean
    public RedissonClient redissonClient() throws IOException {
        try {
            Config config = Config.fromYAML(new ClassPathResource("sentinelRedisson.yml").getInputStream());
            return Redisson.create(config);
        }catch (Exception e){
            log.error("[redisson] redisson未配置！分布式锁相关接口暂无法使用");
            return null;
        }
    }


}
