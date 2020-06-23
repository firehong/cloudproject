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
                // 门户服务接口
                .route(p -> p
                        .path("/apiCar/**")
                        .uri("lb://cgmCarsApi")
                        .id("webapi")
                )
                // 后管系统接口
                .route(p -> p
                        .path("/adminCar/**")
                        .uri("lb://cgmCarsAdmin")
                        .id("adminapi")
                )
                .route(p -> p
                        .path("/admin/**")
                        .uri("lb://cgmCarsAdmin")
                        .id("admin")
                )
                .route(p -> p
                        .path("/market/**")
                        .uri("lb://market")
                        .id("market")
                )
                .route(p -> p
                        .path("/marketAdmin/**")
                        .uri("lb://marketAdmin")
                        .id("marketAdmin")
                )
                .build();
    }

}
