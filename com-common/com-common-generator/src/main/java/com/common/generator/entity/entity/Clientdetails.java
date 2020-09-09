package com.common.generator.entity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author macro
 * @since 2020-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("clientdetails")
public class Clientdetails extends Model<Clientdetails> {

    private static final long serialVersionUID = 1L;

    @TableId("appId")
    private String appId;

    @TableField("resourceIds")
    private String resourceIds;

    @TableField("appSecret")
    private String appSecret;

    @TableField("scope")
    private String scope;

    @TableField("grantTypes")
    private String grantTypes;

    @TableField("redirectUrl")
    private String redirectUrl;

    @TableField("authorities")
    private String authorities;

    @TableField("access_token_validity")
    private Integer accessTokenValidity;

    @TableField("refresh_token_validity")
    private Integer refreshTokenValidity;

    @TableField("additionalInformation")
    private String additionalInformation;

    @TableField("autoApproveScopes")
    private String autoApproveScopes;


    @Override
    protected Serializable pkVal() {
        return this.appId;
    }

}
