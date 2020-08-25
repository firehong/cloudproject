package com.macro.auth.common.base;


import com.common.cache.redis.service.RedisUtil;
import com.macro.auth.api.service.CacheService;
import com.user.api.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Macro
 * @Date 2020/6/28 14:20
 * @Description  service基类
 */
public class BaseService{

    @Autowired
    protected RedisUtil redisUtil;

    @Autowired
    protected HttpServletRequest req;

    @Autowired
    protected UserFeign userFeign;

    @Autowired
    protected CacheService cacheService;


}
