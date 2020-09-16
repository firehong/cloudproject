package com.auth.oauth2.controller;


import com.common.core.response.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther Macro
 * @date 2019/11/5 17:14
 * @Description 获取用户信息
 */
@RestController
@Slf4j
public class Oauth2CodeController {

    /**
     * @auther Macro
     * @date 2019/11/5 17:14
     * @Description 退出登录
     */
    @GetMapping("/oauth/logout")
    public BaseResult wuJinLogout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, null, null);
        try {
            //sending back to client app
            response.sendRedirect(request.getHeader("referer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BaseResult.success();
    }

}
