package com.zdf.hejiayunweb.service;

import com.zdf.internalcommon.response.VerificationCodeResponseDto;
import com.zdf.internalcommon.result.ResponseResult;

/**
* @author mrzhang
* @description Service for generating verification codes
* @createDate 2024-03-31 01:33:13
*/
public interface CaptchaService {
    /**
     * @return ResponseResult<VerificationCodeResponseDto>
     * @author mrzhang
     * @description Get verification code
     * @date 2024/4/11 02:38
     */
    ResponseResult<VerificationCodeResponseDto> getVerificationCode();
}