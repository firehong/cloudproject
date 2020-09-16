package com.auth.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class Oauth2LoginController {

    /**
     * @date 2019/9/24 11:13
     * @Description 自定义登录界面
     * @Param
     */
    @GetMapping("/auth/login")
    public String loginPage(Model model){
        model.addAttribute("loginProcessUrl","/oauth/auth/authorize");
        return "login";
    }

}
