package com.macro.auth.api.service;


import com.common.core.vo.user.UserVO;
import com.macro.auth.api.param.LoginParam;



/**
 * @Author Macro
 * @Date 2020/6/30 14:45
 * @Description 授权相关接口
 */
public interface AuthService {

    /**
     * 用户登录
     * @Param param
     * @Return
     */
    UserVO userLogin(LoginParam param);

}
