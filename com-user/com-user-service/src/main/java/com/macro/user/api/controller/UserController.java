package com.macro.user.api.controller;


import com.macro.common.entity.entity.TbUser;
import com.macro.common.response.BaseResult;
import com.macro.user.common.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Macro
 * @Date 2020/6/28 14:53
 * @Description 用户模块
 */
@RestController
@RequestMapping(value = "/user")
@Api(tags = "用户模块")
public class UserController extends BaseController {

    @ApiOperation(value = "根据用户名获取用户信息", notes = "根据用户名获取用户信息", httpMethod = "GET")
    @GetMapping(value = "/username/{userName}")
    public BaseResult<TbUser> getStoreInfo(@PathVariable String userName){
        return BaseResult.data(userService.selectUserByName(userName));
    }

}
