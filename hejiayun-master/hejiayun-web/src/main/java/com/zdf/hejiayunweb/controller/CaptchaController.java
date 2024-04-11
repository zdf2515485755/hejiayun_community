package com.zdf.hejiayunweb.controller;

import com.zdf.hejiayunweb.service.impl.CaptchaServiceImpl;
import com.zdf.internalcommon.response.VerificationCodeResponseDto;
import com.zdf.internalcommon.result.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *@Description API for operating verification code
 *@Author mrzhang
 *@Date 2024/4/11 02:04
 */

@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Resource
    private CaptchaServiceImpl captchaService;

    @GetMapping("/getVerificationCode")
    public ResponseResult<VerificationCodeResponseDto> getVerificationCode(){
        return captchaService.getVerificationCode();
    }
}