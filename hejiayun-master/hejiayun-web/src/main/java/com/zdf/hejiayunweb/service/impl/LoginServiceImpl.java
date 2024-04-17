package com.zdf.hejiayunweb.service.impl;

import com.zdf.hejiayunweb.mapper.SysMenuMapper;
import com.zdf.hejiayunweb.mapper.SysRoleMapper;
import com.zdf.hejiayunweb.mapper.SysUserMapper;
import com.zdf.hejiayunweb.security.LoginUserDetail;
import com.zdf.hejiayunweb.service.LoginService;
import com.zdf.internalcommon.constant.RedisConstant;
import com.zdf.internalcommon.constant.StatusCode;
import com.zdf.internalcommon.request.LoginRequestDto;
import com.zdf.internalcommon.response.GetUserInfoResponseDto;
import com.zdf.internalcommon.response.UserInfoDto;
import com.zdf.internalcommon.result.ResponseResult;
import com.zdf.internalcommon.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
        Set<String> permitSet = sysMenuMapper.selectMenuByUserId(userId);
        Set<String> roleSet = sysRoleMapper.selectRoleByUserId(userId);
        UserInfoDto userInfoDto = sysUserMapper.queryUserInfo(userId);
        if (userId == 1){
            permitSet.add("*:*:*");
        }
        GetUserInfoResponseDto getUserInfoResponseDto = new GetUserInfoResponseDto();
        getUserInfoResponseDto.setUserInfoDto(userInfoDto);
        getUserInfoResponseDto.setRoleList(roleSet);
        getUserInfoResponseDto.setPermissionList(permitSet);
        return ResponseResult.success(getUserInfoResponseDto);
    }
}