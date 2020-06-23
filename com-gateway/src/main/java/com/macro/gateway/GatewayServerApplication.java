package com.macro.gateway;

import com.macro.gateway.route.properties.GateWayIgnoredUrlProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author Macro
 * @Date 2020/6/23 13:49
 * @Description  主类
 */
@SpringCloudApplication
@EnableConfigurationProperties(GateWayIgnoredUrlProperties.class)
@EnableScheduling
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }
}
