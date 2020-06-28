package com.macro.user.common.base;

import com.macro.user.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Macro
 * @Date 2020/6/28 14:20
 * @Description  controller基类
 */
public class BaseController{

    @Autowired
    protected UserService userService;

}
