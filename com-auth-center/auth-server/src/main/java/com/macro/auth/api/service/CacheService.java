package com.macro.auth.api.service;


import com.common.core.utils.JwtUtils;
import com.common.core.vo.user.UserVO;
import com.macro.auth.common.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther Macro
 * @date 2019/12/26 18:14
 * @Description 缓存服务
 */
@Service
public class CacheService {

    @Autowired
    protected RedisUtil redis;
    @Value("${jwt.config.secret}")
    private String secret;
    @Value("${jwt.config.expireTime}")
    private Long expireTime;

    /**
     * @date 2019/12/26 18:11
     * @Description 设置用户登录态缓存
     * @Param
     */
    public UserVO setUserLoginCache(UserVO userVo){
        String st = userVo.getUid() + userVo.getName() + System.currentTimeMillis();
        // token 生成
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", userVo.getUid());
        claims.put("name", userVo.getName());
        String token = JwtUtils.create(claims, secret, expireTime);
        userVo.setToken(token);
        redis.set(userVo.getToken(), userVo, (long) (1000 * 60 * 60 * 24));
        redis.set(userVo.getUid().toString(), userVo.getToken(), (long) (1000 * 60 * 60 * 24));
        return userVo;
    }

    /**
     * @date 2019/12/26 18:59
     * @Description 刷新用户缓存状态
     * @Param
     */
    public boolean refreshUserLoginCache(String token){
        UserVO uv = redis.get(token);
        if(uv != null){
            redis.set(uv.getToken(), uv, (long)(1000 * 60 * 60 * 24));
            redis.set(uv.getUid(), uv.getToken(), (long)(1000 * 60 * 60 * 24));
            return true;
        }
        return false;
    }

    /**
     * @date 2019/12/26 18:59
     * @Description 刷新用户缓存状态
     * @Param
     */
    public boolean refreshUserLoginCache(UserVO uv){
        if(uv != null){
            redis.set(uv.getToken(), uv, (long)(1000 * 60 * 60 * 24));
            redis.set(uv.getUid(), uv.getToken(), (long)(1000 * 60 * 60 * 24));
            return true;
        }else{
            return false;
        }
    }

    /**
     * @date 2019/12/26 18:14
     * @Description 清楚用户登录缓存
     * @Param
     */
    public void delUserLoginCache(String token){
        UserVO uv = redis.get(token);
        //清除存储token
        redis.del(token);
        redis.del(uv.getUid());
    }

}
