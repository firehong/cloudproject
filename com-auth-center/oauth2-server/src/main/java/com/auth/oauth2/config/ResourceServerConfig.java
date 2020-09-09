package com.auth.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @Author Macro
 * @Date 2020/8/27 11:32
 * @Description 资源管理
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 对 /user 请求进行拦截
        http
            .antMatcher("/oauth/user")
            .authorizeRequests()
            .anyRequest().authenticated();
    }



}
