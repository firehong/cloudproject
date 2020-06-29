package com.macro.user.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.macro.common.entity.entity.TbUser;
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

    private int i = 100;
    @Override
    public TbUser selectUserByName(String userName) {
        boolean lock = redisUtil.getLock(userName, 100l, 1000l, TimeUnit.MILLISECONDS);
        if(lock){
            log.info("i=:{}",i);
            i--;
            QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(TbUser::getName, userName).last(" limit 1");
            redisUtil.unlock(userName);
            return tbUserMapper.selectOne(queryWrapper);
        }else{
            return null;
        }
    }


}
