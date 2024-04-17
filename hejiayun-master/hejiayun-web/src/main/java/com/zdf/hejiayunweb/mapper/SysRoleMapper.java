package com.zdf.hejiayunweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zdf.internalcommon.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
* @author mrzhang
* @description 针对表【sys_role(角色信息表)】的数据库操作Mapper
* @createDate 2024-04-15 23:39:16
* @Entity generator.domain.SysRole
*/
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {
    /**
     * @param roleId:
     * @return List<String>
     * @author mrzhang
     * @description Select role by user id
     * @date 2024/4/16 18:04
     */
    Set<String> selectRoleByUserId(@Param("userId") Long roleId);
}




