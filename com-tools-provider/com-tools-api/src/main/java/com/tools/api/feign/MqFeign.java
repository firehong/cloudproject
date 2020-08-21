package com.tools.api.feign;


import com.tools.api.feign.fallback.MqFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Macro
 * @Date 2020/6/28 13:20
 * @Description 用户相关接口
 */
@FeignClient(value = "mqService", fallbackFactory = MqFeignClientFallbackFactory.class)
public interface MqFeign {

    /**
     * 根据登录账号查找用户
     * @param
     * @return
     */
    @RequestMapping(value = "/mq",method = RequestMethod.GET)
    boolean sendMsg(@RequestParam String key, @RequestParam String msg);



}