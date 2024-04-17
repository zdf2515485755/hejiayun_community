package com.zdf.hejiayunweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zdf.internalcommon.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author mrzhang
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
* @createDate 2024-04-16 01:31:13
*/
public interface SysMenuService extends IService<SysMenuEntity> {
    List<SysMenuEntity> selectMenuByUserId(@Param("userId") Long userId);
}