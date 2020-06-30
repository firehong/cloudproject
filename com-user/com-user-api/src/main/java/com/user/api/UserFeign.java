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
     * 根据登录账号查找用户
     * @param
     * @return
     */
    @RequestMapping(value = "/user/account/{account}",method = RequestMethod.GET)
    TbUser queryUserByAccount(@PathVariable String account);



}