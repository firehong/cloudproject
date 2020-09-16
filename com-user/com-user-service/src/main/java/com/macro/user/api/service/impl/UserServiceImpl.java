package com.macro.user.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.generator.entity.entity.TbUser;
import com.macro.user.api.service.UserService;
import com.macro.user.common.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author Macro
 * @Date 2020/6/28 14:13
 * @Description 用户相关接口实现
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {

    private Logger log = LoggerFactory.getLogger("paylog");

    @Override
    public TbUser queryUserByAccount(String account) {
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TbUser::getAccount, account).last(" limit 1");
        log.info("pay:{}",tbUserMapper.selectOne(queryWrapper));
        return tbUserMapper.selectOne(queryWrapper);
    }


}
