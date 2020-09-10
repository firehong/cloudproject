package com.common.mybatis.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Macro
 * @Date 2020/9/10 15:27
 * @Description  mapper注入
 */
@Configuration
@MapperScan("com.common.generator.entity.mapper")
public class MapperScanConfig {
}
