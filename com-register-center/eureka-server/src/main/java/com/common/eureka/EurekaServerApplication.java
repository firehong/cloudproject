package com.common.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author Macro
 * @Date 2020/6/19 16:37
 * @Description  启动主类
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(EurekaServerApplication.class, args);
    }
}
