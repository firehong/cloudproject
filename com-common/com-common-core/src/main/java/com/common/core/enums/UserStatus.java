package com.common.core.enums;

import lombok.Getter;

/**
 * @auther Macro
 * @date 2019/11/9 9:41
 * @Description 用户状态
 */
@Getter
public enum UserStatus {

    LOCK((byte)1,"冻结状态"),
    NORMAL((byte)0, "正常");


    /**状态值**/
    byte code;
    /**信息描述**/
    String desc;

    UserStatus(byte code, String desc){
        this.code = code;
        this.desc = desc;
    }
}
