package com.macro.user.api.controller;


import com.macro.common.entity.entity.TbUser;
import com.macro.user.common.base.BaseController;
import com.macro.user.interceptor.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Macro
 * @Date 2020/6/28 14:53
 * @Description 用户模块
 */
@RestController
@Api(tags = "用户模块")
@Slf4j
public class UserController extends BaseController {

    @ApiOperation(value = "根据登录账号获取用户信息", notes = "根据登录账号获取用户信息", httpMethod = "GET")
    @GetMapping(value = "/account/{account}")
    @Auth(isAuth = 1)
    public TbUser queryUserByAccount(@PathVariable String account){
        return userService.queryUserByAccount(account);
    }

}
