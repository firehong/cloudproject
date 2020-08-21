package com.common.generator.entity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author macro
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_user")
public class TbUser extends Model<TbUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID>10000
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户昵称
     */
    @TableField("name")
    private String name;

    /**
     * 用户的头像
     */
    @TableField("head")
    private String head;

    /**
     * 用户性别：0-未知 1-男 2-女
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 积分
     */
    @TableField("point")
    private Integer point;

    /**
     * 绑定的手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 密码（保留：方便测试）
     */
    @TableField("password")
    private String password;

    /**
     * 绑定微信登陆
     */
    @TableField("openid_wx")
    private String openidWx;

    /**
     * 微信的unionid
     */
    @TableField("unionid")
    private String unionid;

    /**
     * 备注，冻结的时候可以备注原因
     */
    @TableField("comment")
    private String comment;

    /**
     * 用户状态：0-正常 1-冻结
     */
    @TableField("status")
    private Integer status;

    /**
     * 登陆次数
     */
    @TableField("logins")
    private Integer logins;

    /**
     * 用户登陆时间
     */
    @TableField("login_time")
    private Date loginTime;

    /**
     * 注册时间
     */
    @TableField("create_time")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
