package com.zdf.hejiayunweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zdf.internalcommon.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author mrzhang
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
* @createDate 2024-04-16 01:31:13
* @Entity generator.domain.SysMenu
*/
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {
    List<SysMenuEntity> selectMenuByUserId(@Param("userId") Long userId);
}