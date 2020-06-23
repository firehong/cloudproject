package com.macro.gateway.route.handler;

import lombok.Data;

/**
 * @auther Macro
 * @date 2019/11/5 17:20
 * @Description 自定义错误信息
 */
@Data
public class GateWayException extends RuntimeException{

    String error;
    Integer code;

    public GateWayException(Integer code, String error) {
        this.error = error;
        this.code = code;
    }
}
