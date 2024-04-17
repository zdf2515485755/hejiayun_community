package com.zdf.hejiayunweb.service;

import com.zdf.internalcommon.request.LoginRequestDto;
import com.zdf.internalcommon.response.GetUserInfoResponseDto;
import com.zdf.internalcommon.result.ResponseResult;

/**
 * @Description 类功能简要描述
 * @Author mrzhang
 * @Date 2024/4/12 01:12
 */
public interface LoginService {
    /**
     * @param loginRequestDto:
     * @return ResponseResult<String>
     * @author mrzhang
     * @description Implement login authentication
     * @date 2024/4/12 01:25
     */
    ResponseResult<String> login(LoginRequestDto loginRequestDto);
    /**
     * @param userId:
     * @return ResponseResult<GetUserInfoResponseDto>
     * @author mrzhang
     * @description get user info
     * @date 2024/4/16 23:38
     */
    ResponseResult<GetUserInfoResponseDto> getUserInfo(Long userId);
}
