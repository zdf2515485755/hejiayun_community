package com.zdf.hejiayunweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zdf.internalcommon.entity.SysRoleEntity;

import java.util.Set;

/**
* @author mrzhang
* @description 针对表【sys_role(角色信息表)】的数据库操作Service
* @createDate 2024-04-15 23:39:16
*/
public interface SysRoleService extends IService<SysRoleEntity> {
    /**
     * @param userId:
     * @return List<String>
     * @author mrzhang
     * @description Select role by user id
     * @date 2024/4/16 18:07
     */
    Set<String> selectRoleByUserId(Long userId);
}
