package com.zdf.hejiayunweb.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zdf.internalcommon.entity.SysUserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 *@Description 类功能简要描述
 *@Author mrzhang
 *@Date 2024/4/12 19:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDetail implements UserDetails {

    private SysUserEntity sysUserEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return sysUserEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUserEntity.getUserName();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}