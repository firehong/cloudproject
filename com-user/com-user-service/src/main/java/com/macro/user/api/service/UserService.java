package com.macro.user.api.service;

import com.macro.common.entity.entity.TbUser;

/**
 * @Author Macro
 * @Date 2020/6/28 14:14
 * @Description  用户相关接口
 */
public interface UserService {

    /**
     * 根据用户名查询用户
     * @Param userName
     * @Return
     */
    TbUser selectUserByName(String userName);

}
