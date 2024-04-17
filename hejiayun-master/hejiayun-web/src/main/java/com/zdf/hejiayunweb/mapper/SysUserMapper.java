package com.zdf.hejiayunweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zdf.internalcommon.entity.SysUserEntity;
import com.zdf.internalcommon.response.UserInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author mrzhang
* @description 针对表【sys_user(用户信息表)】的数据库操作Mapper
* @createDate 2024-04-12 00:42:16
* @Entity generator.domain.SysUser
*/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
    UserInfoDto queryUserInfo(@Param("userId") Long userId);
}




