package com.zdf.hejiayunweb.controller;

import com.zdf.hejiayunweb.service.impl.LoginServiceImpl;
import com.zdf.internalcommon.request.LoginRequestDto;
import com.zdf.internalcommon.result.ResponseResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *@Description API for operating login
 *@Author mrzhang
 *@Date 2024/4/12 01:08
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    private LoginServiceImpl loginService;

    @PostMapping("/login")
    public ResponseResult<String> login(@Validated @RequestBody LoginRequestDto loginRequestDto) {
        return loginService.login(loginRequestDto);
    }
}
