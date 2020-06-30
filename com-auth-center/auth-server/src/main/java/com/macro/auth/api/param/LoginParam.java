package com.macro.auth.api.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value="用户登录请求实例")
public class LoginParam {

    @ApiModelProperty(value="登录账号")
    @NotBlank(message = "登录账号不能为空")
    private String account;

    @ApiModelProperty(value="登录密码")
    @NotBlank(message = "登录密码不能为空")
    private String password;

    @ApiModelProperty(value="登录验证码")
    @NotBlank(message = "登录验证码不能为空")
    private String vcode;


}
