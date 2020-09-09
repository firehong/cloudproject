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
 * 用户表
 * </p>
 *
 * @author macro
 * @since 2020-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_user")
public class TbUser extends Model<TbUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId("id")
    private Long id;

    /**
     * 用户名
     */
    @TableField("name")
    private String name;

    /**
     * 登录账号
     */
    @TableField("account")
    private String account;

    /**
     * 登录密码
     */
    @TableField("password")
    private String password;

    /**
     * 加密盐
     */
    @TableField("password_salt")
    private String passwordSalt;

    /**
     * 0 正常 1；冻结
     */
    @TableField("status")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
