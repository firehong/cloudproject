package com.macro.auth.interceptor;


import com.common.core.vo.user.UserVO;

/**
 * @Author Macro
 * @Date 2020/7/1 15:04
 * @Description  用户登录信息缓存
 */
public class UserContext {

    // 缓存池
    private static ThreadLocal<UserVO> adminHolder = new ThreadLocal<>();

    public static void setUser(UserVO userVO) {
        adminHolder.set(userVO);
    }

    public static UserVO getUser() {
        return adminHolder.get();
    }

}
