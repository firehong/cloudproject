package com.macro.gateway.route.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther Macro
 * @date 2019/8/1 13:12
 * @Description 路由规则配置
 */
@Configuration
public class Routes {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
//                // 授权服务
//                .route(p -> p
//                        .path("/auth/**")
//                        .uri("lb://authService")
//                        .id("authApi")
//                )
                // 用户模块
//                .route(p -> p
//                        .path("/user/**")
//                        .uri("lb://userService")
//                        .id("userApi")
//                )
                .build();
    }

}
