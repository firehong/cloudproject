package com.macro.user.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.generator.entity.entity.TbUser;
import com.macro.user.api.service.UserService;
import com.macro.user.common.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author Macro
 * @Date 2020/6/28 14:13
 * @Description 用户相关接口实现
 */
@Service
@Slf4j
public class UserServiceImpl extends BaseService implements UserService {

    @Override
    public TbUser queryUserByAccount(String account) {
        redisUtil.getLock(account, 100L, 1000L, TimeUnit.MILLISECONDS);
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TbUser::getName, account).last(" limit 1");
        return tbUserMapper.selectOne(queryWrapper);
    }


}
