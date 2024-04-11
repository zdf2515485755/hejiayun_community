package com.zdf.hejiayunweb.service.impl;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.zdf.hejiayunweb.service.CaptchaService;
import com.zdf.internalcommon.response.VerificationCodeResponseDto;
import com.zdf.internalcommon.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
* @author mrzhang
* @description Service for generating verification codes
* @createDate 2024-03-31 01:33:13
*/
@Service
@Slf4j
public class CaptchaServiceImpl implements CaptchaService {

    @Resource
    private  RedisTemplate<String, Object> redisTemplate;

    @Override
    public ResponseResult<VerificationCodeResponseDto> getVerificationCode() {
        //生成验证码
        Captcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verificationCodeKey = UUID.randomUUID().toString();
        String verificationCode = specCaptcha.text().toLowerCase();
        log.info(verificationCode);
        // 存入redis
        redisTemplate.opsForValue().set(verificationCodeKey, verificationCode, 120, TimeUnit.SECONDS);

        VerificationCodeResponseDto verificationCodeResponseDto = VerificationCodeResponseDto.builder().uuid(verificationCodeKey)
                .image(specCaptcha.toBase64())
                .build();

        return ResponseResult.success(verificationCodeResponseDto);
    }
}