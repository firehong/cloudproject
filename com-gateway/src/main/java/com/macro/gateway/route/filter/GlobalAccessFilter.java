package com.macro.gateway.route.filter;


import com.macro.gateway.route.context.GatewayContext;
import com.macro.gateway.route.handler.GateWayException;
import com.macro.gateway.route.properties.GateWayIgnoredUrlProperties;
import com.macro.gateway.util.IpUtil;
import com.macro.gateway.util.JwtUtils;
import com.macro.gateway.util.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @auther Macro
 * @date 2019/11/5 17:19
 * @Description 路由权限判断过滤
 */
@Slf4j
@Component
public class GlobalAccessFilter implements GlobalFilter, Ordered {

    @Resource
    private GateWayIgnoredUrlProperties ignoredUrl;
    @Autowired
    private RedisUtil redis;
    @Value("${jwt.config.secret}")
    private String secret;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * @date 2019/10/8 11:19
     * @Description token校验
     * @Param
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            ServerHttpRequest request= exchange.getRequest();
            //获取请求上下文
            GatewayContext gatewayContext = exchange.getAttribute(GatewayContext.CACHE_GATEWAY_CONTEXT);
            String path = gatewayContext.getPath();
            log.info("[ route ] 请求地址: {}, 请求请求目标url: {}", IpUtil.getIpAddress(request), path);
            //检查是否为白名单路径
            boolean flag = whiteVerify(path);
            if(flag){
                //1. 白名单url 放行
                return chain.filter(exchange);
            }else{
                ServerHttpResponse response=exchange.getResponse();
                // 权限判断
                if(gatewayContext.getRequestHeaders().get("token") != null){
                    String token = gatewayContext.getRequestHeaders().get("token").get(0);
                    if(verifyAccessToken(token)){
                        //验证通过 访问
                        return chain.filter(exchange);
                    }
                }
                return unAuthorizedResponse(response, "没有产品权限，请先开通");
            }
        }catch (Exception e){
            if(e instanceof GateWayException){
                GateWayException gateWayException = (GateWayException) e;
                return unAuthorizedResponse(exchange.getResponse(), gateWayException.getError());
            }
            log.error(" 权限过滤异常：{}", e.getMessage(), e);
            return unAuthorizedResponse(exchange.getResponse(), "权限检测异常");
        }
    }

    /**
     * @date 2019/10/8 13:45
     * @Description 认证失败响应
     * @Param
     */
    private Mono<Void> unAuthorizedResponse(ServerHttpResponse response, String error){
        throw new GateWayException(20001012,error);
    }

    /**
     * @date 2019/10/8 14:20
     * @Description 白名单检查
     * @Param
     */
    public boolean whiteVerify(String path){
        String[] ignored=ignoredUrl.getIgnored();
        boolean flag=false;
        for(String temp:ignored){
            if(pathMatcher.match(temp,path)){
                flag=true;
                break;
            }
        }
        return flag;
    }

    /**
     * @date 2019/10/8 14:16
     * @Description token权限验证
     * @Param
     */
    protected boolean verifyAccessToken(String token){
        //token有效性验证
        return JwtUtils.validate(token, secret);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
