package com.auth.oauth2.config;

import com.auth.oauth2.service.MyUserDetailsService;
import com.common.cache.redis.service.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.common.core.utils.PasswordEncoder.createPassword;

/**
 * @auther Macro
 * @date 2019/9/23 16:01
 * @Description 验证用户账号密码，对请求路劲做处理
 */
@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private RedisUtil redis;

    /**
     * @date 2019/10/8 8:58
     * @Description 自定义密码校验
     * @Param
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return null;
            }
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                // 获取token 验证appid appsecret
                if(s.contains("$")){
                    return new BCryptPasswordEncoder().matches(charSequence, s);
                }
                //验证用户登录账户密码
                try {
                    //获取密码盐
                    String key = redis.get(s)==null ? null : redis.get(s).toString();
                    if(key==null){
                        return false;
                    }
                    String salt = redis.get(key)==null ? null : redis.get(key).toString();
                    if(salt==null){
                        return false;
                    }
                    // 生成加密密文
                    String str = createPassword(charSequence.toString(), salt);
                    //密码对比
                    return str.equals(s);
                } catch (Exception e) {
                    log.error("[security] 自定义密码校验异常：{}", e.getMessage(), e);
                    return false;
                }
            }
        };
    }

    /**
     * 认证管理
     * 将 AuthenticationManager 注册为 bean , 方便配置 oauth server 的时候使用
     * @return 认证管理对象
     * @throws Exception 认证异常信息
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @date 2019/9/24 10:00
     * @Description 自定义登录验证逻辑
     * @Param
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * http安全配置
     *
     * @param http http安全对象
     * @throws Exception http安全异常信息
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll();
    }


}
