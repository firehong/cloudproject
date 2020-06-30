package com.macro.auth.common.base;


import com.macro.auth.common.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Macro
 * @Date 2020/6/28 14:20
 * @Description  service基类
 */
public class BaseService{

    @Autowired
    protected RedisUtil redisUtil;


}
