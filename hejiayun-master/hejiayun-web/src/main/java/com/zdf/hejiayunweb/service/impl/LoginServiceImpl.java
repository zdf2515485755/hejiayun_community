package com.zdf.hejiayunweb.service.impl;

import com.zdf.hejiayunweb.mapper.SysMenuMapper;
import com.zdf.hejiayunweb.mapper.SysRoleMapper;
import com.zdf.hejiayunweb.mapper.SysUserMapper;
import com.zdf.hejiayunweb.security.LoginUserDetail;
import com.zdf.hejiayunweb.service.LoginService;
import com.zdf.internalcommon.constant.MenuConstant;
import com.zdf.internalcommon.constant.RedisConstant;
import com.zdf.internalcommon.constant.StatusCode;
import com.zdf.internalcommon.entity.MetaEntity;
import com.zdf.internalcommon.entity.SysMenuEntity;
import com.zdf.internalcommon.request.LoginRequestDto;
import com.zdf.internalcommon.response.GetMenuResponseDto;
import com.zdf.internalcommon.response.GetUserInfoResponseDto;
import com.zdf.internalcommon.response.MenuResponseDto;
import com.zdf.internalcommon.response.UserInfoDto;
import com.zdf.internalcommon.result.ResponseResult;
import com.zdf.internalcommon.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 *@Description Service for login
 *@Author mrzhang
 *@Date 2024/4/12 01:12
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysUserMapper sysUserMapper;

    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public ResponseResult<String> login(LoginRequestDto loginRequestDto) {
        String redisKey = loginRequestDto.getUuid();
        if (Objects.isNull(redisKey)){
            return ResponseResult.fail(StatusCode.REDIS_KEY_IS_EMPTY.getCode(), StatusCode.REDIS_KEY_IS_EMPTY.getMessage());
        }
        String verifyCode = (String)redisTemplate.opsForValue().get(RedisConstant.VERIFY_CODE_KEY_PREFIX + redisKey);
        if (Objects.isNull(verifyCode)){
            return ResponseResult.fail(StatusCode.VERIFY_CODE_IS_EMPTY.getCode(), StatusCode.VERIFY_CODE_IS_EMPTY.getMessage());
        }

        if (!loginRequestDto.getVerificationCode().trim().equalsIgnoreCase(verifyCode.trim())){
            return ResponseResult.fail(StatusCode.VERIFY_CODE_IS_ERROR.getCode(), StatusCode.VERIFY_CODE_IS_ERROR.getMessage());
        }
        //将username和password封装成Authentication 对象进行认证
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUserName(), loginRequestDto.getPassword()));
        }catch (Exception exception){
            log.error("Authentication failed");
            return ResponseResult.fail(StatusCode.AUTHENTICATION_ERROR.getCode(), StatusCode.AUTHENTICATION_ERROR.getMessage(),null);
        }
        LoginUserDetail loginUserDetail = (LoginUserDetail)authenticate.getPrincipal();
        redisTemplate.opsForValue().set(RedisConstant.USER_INFO_KEY_PREFIX + loginUserDetail.getUsername(), loginUserDetail, RedisConstant.EXPIRE_TIME, TimeUnit.DAYS);
        return ResponseResult.success(JwtUtil.generatorToken(loginUserDetail.getUsername(), loginUserDetail.getPassword()));
    }

    @Override
    public ResponseResult<GetUserInfoResponseDto> getUserInfo(Long userId) {
        List<SysMenuEntity> sysMenuEntities = sysMenuMapper.selectMenuByUserId(userId);
        Set<String> permitSet = new HashSet<>();
        Set<String> roleSet = sysRoleMapper.selectRoleByUserId(userId);
        UserInfoDto userInfoDto = sysUserMapper.queryUserInfo(userId);
        if (userId == 1){
            sysMenuEntities.forEach(
                    sysMenuEntity -> permitSet.add("*:*:*")
            );
        }else {
            sysMenuEntities.forEach(
                    sysMenuEntity -> permitSet.add(sysMenuEntity.getPerms())
            );
        }
        GetUserInfoResponseDto getUserInfoResponseDto = new GetUserInfoResponseDto();
        getUserInfoResponseDto.setUserInfoDto(userInfoDto);
        getUserInfoResponseDto.setRoleList(roleSet);
        getUserInfoResponseDto.setPermissionList(permitSet);
        return ResponseResult.success(getUserInfoResponseDto);
    }

    @Override
    public ResponseResult<List<GetMenuResponseDto>> getMenu(Long userId ){
        List<SysMenuEntity> sysMenuEntities = sysMenuMapper.selectMenuByUserId(userId);
        List<MenuResponseDto> menuResponseDtoList = sysMenuEntities.stream().filter(menu -> menu.getParentId() == 0)
                .map(metaEntity -> {
                    MenuResponseDto menuResponseDto = new MenuResponseDto();
                    BeanUtils.copyProperties(metaEntity, menuResponseDto);
                    menuResponseDto.setChildrenList(getChildList(metaEntity, sysMenuEntities));
                    return menuResponseDto;
                }).collect(Collectors.toList());
        return ResponseResult.success(generateMenuTree(menuResponseDtoList));
    }

    private List<GetMenuResponseDto> generateMenuTree(List<MenuResponseDto> menuResponseDtoList) {
        return menuResponseDtoList.stream().map(
                menuResponseDto -> {
                    GetMenuResponseDto getMenuResponseDto = new GetMenuResponseDto();
                    getMenuResponseDto.setName(menuResponseDto.getPath());
                    getMenuResponseDto.setPath(getPath(menuResponseDto));
                    getMenuResponseDto.setHidden(MenuConstant.MENU_TYPE_HIDDEN == Integer.parseInt(menuResponseDto.getVisible()));
                    getMenuResponseDto.setComponent(getComponent(menuResponseDto));
                    getMenuResponseDto.setMetaEntity(new MetaEntity(menuResponseDto.getMenuName(), menuResponseDto.getIcon(), MenuConstant.MENU_IS_CACHE.equals(menuResponseDto.getIsCache())));
                    if (!menuResponseDto.getChildrenList().isEmpty()){
                        getMenuResponseDto.setAlwaysShow(true);
                        getMenuResponseDto.setRedirect(MenuConstant.MENU_PATH_DIRECT);
                        getMenuResponseDto.setChildren(generateMenuTree(menuResponseDto.getChildrenList()));
                    }
                    return getMenuResponseDto;
                }
        ).collect(Collectors.toList());
    }

    private String getComponent(MenuResponseDto menuResponseDto) {
        if (Objects.isNull(menuResponseDto.getComponent())) {
            return MenuConstant.MENU_ROOT_PATH;
        } else if (menuResponseDto.getParentId().intValue() != MenuConstant.MENU_ROOT && Objects.equals(menuResponseDto.getMenuType(), MenuConstant.MENU_TYPE_CATALOGUE)) {
            return MenuConstant.MENU_CHILD_PATH;
        }
        return menuResponseDto.getComponent();
    }

    private String getPath(MenuResponseDto menuResponseDto) {
        if (menuResponseDto.getParentId().intValue() == MenuConstant.MENU_ROOT && Objects.equals(menuResponseDto.getMenuType(), MenuConstant.MENU_TYPE_CATALOGUE)){
            return "/"+ menuResponseDto.getPath();
        }
        return menuResponseDto.getPath();
    }

    private List<MenuResponseDto> getChildList(SysMenuEntity metaEntity, List<SysMenuEntity> sysMenuEntities) {
        List<SysMenuEntity> childList = sysMenuEntities.stream().filter(menu -> Objects.equals(menu.getParentId(), metaEntity.getMenuId()))
                .collect(Collectors.toList());
        if (!childList.isEmpty()) {
            return childList.stream().map(meunEntity->{
                MenuResponseDto menuResponseDto = new MenuResponseDto();
                BeanUtils.copyProperties(meunEntity, menuResponseDto);
                menuResponseDto.setChildrenList(getChildList(meunEntity, sysMenuEntities));
                return menuResponseDto;
            }).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}