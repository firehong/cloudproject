package com.macro.common.entity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author macro
 * @since 2020-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
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
    private String name;

    /**
     * 用户的头像
     */
    private String head;

    /**
     * 用户性别：0-未知 1-男 2-女
     */
    private Integer sex;

    /**
     * 积分
     */
    private Integer point;

    /**
     * 绑定的手机号
     */
    private String phone;

    /**
     * 密码（保留：方便测试）
     */
    private String password;

    /**
     * 绑定微信登陆
     */
    private String openidWx;

    /**
     * 微信的unionid
     */
    private String unionid;

    /**
     * 备注，冻结的时候可以备注原因
     */
    private String comment;

    /**
     * 用户状态：0-正常 1-冻结
     */
    private Integer status;

    /**
     * 登陆次数
     */
    private Integer logins;

    /**
     * 用户登陆时间
     */
    private Date loginTime;

    /**
     * 注册时间
     */
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
