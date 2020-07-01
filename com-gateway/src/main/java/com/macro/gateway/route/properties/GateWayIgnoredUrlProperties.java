package com.macro.gateway.route.properties;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author Macro
 * @Date 2020/6/23 14:01
 * @Description
 */
@ConfigurationProperties(prefix = "com.gateway")
public class GateWayIgnoredUrlProperties {
    /**
     * 监控中心和swagger需要访问的url
     */
    private static final String[] ENDPOINTS = {
            "/actuator/**",
            "/**/actuator/**" ,
            "/**/actuator/**/**" ,  //断点监控
            "/**/turbine.stream",
            "/**/turbine.stream**/**",
            "/**/hystrix",
            "/**/hystrix.stream",
            "/**/hystrix/**",
            "/**/hystrix/**/**",
            "/**/proxy.stream/**" , //熔断监控
            "/**/druid/**",
            "/**/favicon.ico",
            "/auth/**",  //授权服务
    };

    private String[] ignored;

    /**
     * 需要放开权限的url
     *      自定义的url
     * @return 自定义的url和监控中心需要访问的url集合
     */
    public String[] getIgnored() {
        if(this.ignored ==null||this.ignored.length==0){
            return ENDPOINTS;
        }
        String[] path=ArrayUtils.addAll(this.ignored,ENDPOINTS);
        return path;
    }

    public void setIgnored(String[] ignored) {
        this.ignored = ignored;
    }

}
