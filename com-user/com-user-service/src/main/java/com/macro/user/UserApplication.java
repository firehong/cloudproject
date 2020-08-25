package com.macro.user;

import com.common.cache.annotation.EnableRedisLockServer;
import com.common.cache.annotation.EnableRedisServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @auther Macro
 * @date 2019/11/9 9:54
 * @Description 启动主类
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.common.generator.entity.mapper")
@EnableAsync
@EnableRedisServer
@EnableRedisLockServer
public class UserApplication{

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
