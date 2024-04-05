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

/**
 * 区域表
 * @author mrzhang
 * @TableName sys_area
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_area")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysAreaEntity extends BaseEntity implements Serializable {
    /**
     * 唯一主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 城市编码
     */
    private Integer code;

    /**
     * 城市名称
     */
    private String name;

    /**
     * 城市父ID
     */
    private Integer parentId;

    /**
     * 城市等级(0:省,1:市,2:县)
     */
    private Integer level;

    /**
     * 功能类型(1:角色认证地点开通;0:未开通)
     */
    private Integer type;

    /**
     * 服务状态
     */
    private Integer serviceState;

    /**
     * 逻辑删除 0正常 1 删除
     */
    private Integer deleteFlag;

    /**
     * 地区范围
     */
    private Integer region;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}