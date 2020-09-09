package com.macro.user.common.base;


import com.common.cache.lock.service.RedisLockUtil;
import com.common.cache.redis.service.RedisUtil;

import com.common.generator.entity.mapper.TbUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Macro
 * @Date 2020/6/28 14:20
 * @Description  service基类
 */
public class BaseService{

    @Autowired
    protected BaseDao baseDao;

    @Autowired
    protected HttpServletRequest req;

    @Autowired
    protected RedisUtil redisUtil;

    @Autowired
    protected RedisLockUtil redisLockUtil;

    @Autowired
    protected TbUserMapper tbUserMapper;

}
