package com.macro.common.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="用户登录态信息")
public class UserVO implements Serializable {

    private static final long serialVersionUID = 9039640674543959624L;
    @ApiModelProperty(value="用户ID")
    private Integer uid;
    @ApiModelProperty(value="用户类型")
    private Byte roleType;
    @ApiModelProperty(value="身份")
    private Byte identify;
    @ApiModelProperty(value="用户名")
    private String name;
    @ApiModelProperty(value="登录令牌")
    private String token;

}
