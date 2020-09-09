package com.macro.auth.api.controller;

import com.common.core.response.BaseResult;
import com.common.core.vo.user.UserVO;
import com.macro.auth.api.param.LoginParam;
import com.macro.auth.common.base.BaseController;
import com.macro.auth.interceptor.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author Macro
 * @Date 2020/6/30 9:44
 * @Description 用户认证模块
 */
@RestController
@Api(tags = "用户认证模块")
public class AuthController extends BaseController {


    @ApiOperation(value = "登录认证", notes = "登录认证", httpMethod = "POST")
    @PostMapping(value = "/login")
    @Auth()
    public BaseResult<UserVO> userLoginByPassword(@Valid @RequestBody LoginParam param){
        UserVO userVO = authService.userLogin(param);
        return BaseResult.data(userVO);
    }




}
