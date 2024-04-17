package com.zdf.hejiayunweb.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zdf.hejiayunweb.mapper.SysMenuMapper;
import com.zdf.hejiayunweb.mapper.SysUserMapper;
import com.zdf.internalcommon.constant.HjyCommunityConstant;
import com.zdf.internalcommon.entity.SysMenuEntity;
import com.zdf.internalcommon.entity.SysUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *@Description 从数据库中获取对应用户账号信息，返回给security进行认证
 *@Author mrzhang
 *@Date 2024/4/12 19:24
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUserEntity> sysUserEntityQueryWrapper = new QueryWrapper<>();
        sysUserEntityQueryWrapper.and(wrapper->wrapper
                        .eq("user_name", username)
                        .eq("status", 0)
                        .eq("del_flag", 0)
                );
        SysUserEntity sysUserEntity = sysUserMapper.selectOne(sysUserEntityQueryWrapper);

        if (Objects.isNull(sysUserEntity)) {
            log.error("user does not exist");
            throw new UsernameNotFoundException("user does not exist");
        }
        //获取用户权限信息
        List<SysMenuEntity> sysMenuEntities = sysMenuMapper.selectMenuByUserId(sysUserEntity.getUserId());
        Set<String> permitSet = new HashSet<>();
        if (Objects.equals(username, HjyCommunityConstant.UPDATE_INSERT_AUTHOR)){
            sysMenuEntities.forEach(
                    sysMenuEntity -> permitSet.add("*:*:*")
            );
        }else {
            sysMenuEntities.forEach(
                    sysMenuEntity -> permitSet.add(sysMenuEntity.getPerms())
            );
        }
        return new LoginUserDetail(sysUserEntity, permitSet);
    }
}