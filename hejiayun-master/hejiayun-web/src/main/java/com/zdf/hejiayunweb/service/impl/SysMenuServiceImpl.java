package com.zdf.hejiayunweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zdf.hejiayunweb.mapper.SysMenuMapper;
import com.zdf.hejiayunweb.service.SysMenuService;
import com.zdf.internalcommon.entity.SysMenuEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
* @author mrzhang
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
* @createDate 2024-04-16 01:31:13
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity>
    implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public Set<String> selectMenuByUserId(Long userId) {
        return sysMenuMapper.selectMenuByUserId(userId);
    }
}




