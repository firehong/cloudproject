package com.auth.oauth2.service;

import com.common.cache.redis.service.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author Macro
 * @Date 2020/8/27 11:42
 * @Description  security 用户匹配查询
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = new User(s,"8C7D3F4326E4C9E261132065B177A5B9765C4DE6",
                AuthorityUtils.commaSeparatedStringToAuthorityList("USER"));
        //缓存加密密码盐 有效期10s
        redisUtil.set("8C7D3F4326E4C9E261132065B177A5B9765C4DE6", "123", 10);
        redisUtil.set("123", "123", 10);
        return user;
    }


}
