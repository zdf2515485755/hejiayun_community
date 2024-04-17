package com.zdf.hejiayunweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zdf.hejiayunweb.mapper.SysRoleMapper;
import com.zdf.hejiayunweb.service.SysRoleService;
import com.zdf.internalcommon.entity.SysRoleEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
* @author mrzhang
* @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
* @createDate 2024-04-15 23:39:16
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity>
    implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public Set<String> selectRoleByUserId(Long userId) {
        return sysRoleMapper.selectRoleByUserId(userId);
    }
}




