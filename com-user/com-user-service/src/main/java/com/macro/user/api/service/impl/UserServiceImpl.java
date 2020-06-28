package com.macro.user.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.macro.common.entity.entity.TbUser;
import com.macro.user.api.service.UserService;
import com.macro.user.common.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * @Author Macro
 * @Date 2020/6/28 14:13
 * @Description 用户相关接口实现
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Override
    public TbUser selectUserByName(String userName) {
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TbUser::getName, userName).last(" limit 1");
        return tbUserMapper.selectOne(queryWrapper);
    }


}
