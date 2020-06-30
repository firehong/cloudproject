package com.macro.auth.api.controller;


import com.macro.auth.common.base.BaseController;
import com.macro.common.entity.entity.TbUser;
import com.macro.common.response.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Macro
 * @Date 2020/6/30 9:44
 * @Description 用户认证模块
 */
@RestController
@RequestMapping(value = "/auth")
@Api(tags = "用户认证模块")
public class AuthController extends BaseController {

    @ApiOperation(value = "账户密码登录认证", notes = "账户密码登录认证", httpMethod = "POST")
    @PostMapping(value = "/login")
    public BaseResult<TbUser> userLoginByPassword(@RequestParam String name){
        return BaseResult.data(userFeign.queryUserByName(name));
    }


}
