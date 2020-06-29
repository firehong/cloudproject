package com.macro.user.common.util.redis.config;

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
public class RedissonConfiguration {

    /**
     * redisson客户端
     * @return
     * @throws IOException
     */
    @Bean
    public RedissonClient redissonClient() throws IOException {
        Config config = Config.fromYAML(new ClassPathResource("sentinelRedisson.yml").getInputStream());
        return Redisson.create(config);
    }


}
