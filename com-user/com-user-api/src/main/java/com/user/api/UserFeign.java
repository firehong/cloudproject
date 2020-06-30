package com.user.api;

import com.macro.common.entity.entity.TbUser;
import com.user.api.fallback.UserFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author Macro
 * @Date 2020/6/28 13:20
 * @Description 用户相关接口
 */
@FeignClient(value = "userService", fallbackFactory = UserFeignClientFallbackFactory.class)
public interface UserFeign {


    /**
     * 根据用户名查找用户
     * @param
     * @return
     */
    @RequestMapping(value = "/user/username/{name}",method = RequestMethod.GET)
    TbUser queryUserByName(@PathVariable String name);



}