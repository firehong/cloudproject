package com.macro.auth;

import com.common.cache.annotation.EnableRedisServer;
import com.common.swagger.annotation.EnableMacroSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @auther Macro
 * @date 2019/11/9 9:54
 * @Description 启动主类
 */
@EnableFeignClients(basePackages = {"com.user.api.**"})
@SpringBootApplication
@EnableDiscoveryClient
@EnableMacroSwagger2
@EnableRedisServer
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
