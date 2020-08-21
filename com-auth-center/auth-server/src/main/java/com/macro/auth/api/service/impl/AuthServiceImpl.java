package com.macro.auth.api.service.impl;

import com.common.core.enums.UserStatus;
import com.common.core.exception.MyException;
import com.common.core.exception.SystemError;
import com.common.core.vo.user.UserVO;
import com.common.generator.entity.entity.TbUser;
import com.macro.auth.api.param.LoginParam;
import com.macro.auth.api.service.AuthService;
import com.macro.auth.common.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl extends BaseService implements AuthService {

    @Override
    public UserVO userLogin(LoginParam param) {
        // 获取用户信息
        TbUser tbUser = userFeign.queryUserByAccount(param.getAccount());
        if(tbUser == null){
            throw new MyException(SystemError.USER_NOT_EXIST);
        }
        if(tbUser.getStatus() == UserStatus.LOCK.getCode()){
            throw new MyException(SystemError.USER_LOCKED);
        }
        UserVO userVO = UserVO.builder()
                .name(tbUser.getName())
                .uid(tbUser.getId())
                .build();
        //缓存
        userVO = cacheService.setUserLoginCache(userVO);
        return userVO;
    }



}
