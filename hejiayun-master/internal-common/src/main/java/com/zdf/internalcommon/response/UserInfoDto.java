package com.zdf.internalcommon.response;

import com.zdf.internalcommon.entity.SysDeptEntity;
import com.zdf.internalcommon.entity.SysRoleEntity;
import com.zdf.internalcommon.entity.SysUserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *@Description user info
 *@Author mrzhang
 *@Date 2024/4/16 21:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto extends SysUserEntity {
    private SysDeptEntity sysDeptEntity;
    private List<SysRoleEntity> sysRoleEntityList;
    private Long[] roleIds;
    private Long[] postIds;
}
