package com.macro.user.common.base;

import com.macro.common.entity.mapper.TbUserMapper;
import com.macro.user.common.util.redis.RedisUtil;
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
    protected TbUserMapper tbUserMapper;

}
