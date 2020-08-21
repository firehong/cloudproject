package com.macro.user.api.service;


import com.common.generator.entity.entity.TbUser;

/**
 * @Author Macro
 * @Date 2020/6/28 14:14
 * @Description  用户相关接口
 */
public interface UserService {

    /**
     * 根据登录账号查询用户
     * @Param userName
     * @Return
     */
    TbUser queryUserByAccount(String account);

}
