package com.macro.auth.interceptor;

import java.lang.annotation.*;

/**
 * @auther Macro
 * @date 2019-07-03 11:39
 * @description 自定义权限判断
 */
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {

    /**是否需要鉴权 0-不需要 1-需要**/
    int isAuth() default 0;
    /**是否需要日志记录 0-不需要 1-需要**/
    int isLog() default 1;
    /**角色权限**/
    String role() default "";
    /**权限码，不可单独使用，需要配合isAuth使用**/
    String authCode() default "";
    /**方法说明**/
    String desc() default "";



}


