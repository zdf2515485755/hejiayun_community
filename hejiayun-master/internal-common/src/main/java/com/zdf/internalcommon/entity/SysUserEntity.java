package com.zdf.internalcommon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 * @author mrzhang
 * @TableName sys_user
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserEntity extends BaseEntity implements Serializable {
    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Long userId;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 用户账号
     */
    private String userName;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户类型（00系统用户）
     */
    private String userType;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String phonenumber;
    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 密码
     */
    private String password;
    /**
     * 账号状态（0正常 1停用）
     */
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;
    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    private Date loginDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}