package com.common.core.exception;

import lombok.Getter;

/**
 * 定义返回错误码
 *
 * @author macro
 * @version share 1.0
 */
@Getter
public enum SystemError {

    //系统相关
    SERVER_ERROR(50000, "服务器开小差了~~"),
    PARAM_ERROR(50002, "参数错误"),
    OPERATION_FAIL(50007, "操作失败"),
    OPERATION_REPEAT(50008, "重复操作"),
    NO_LOGIN(50010, "请先登录"),
    RECORD_EXIST(50020, "记录已存在"),
    ILLEGAL_OPERATION(50030, "非法操作"),


    /***用户相关***/
    USER_NOT_EXIST(10001, "用户不存在,请先前往注册"),
    PASSWORD_ERROR(10002, "密码不正确"),
    USER_LOCKED(10003, "用户已被锁定"),
    VCODE_ERROR(10004, "验证码不正确"),
    PHONE_REGISTER(10010, "手机号已经被注册"),




    ;


    /**
     * 状态码
     **/
    private int code;
    /**
     * 信息
     **/
    private String message;

    SystemError(int code, String message) {
        this.code = code;
        this.message = message;
    }



}
