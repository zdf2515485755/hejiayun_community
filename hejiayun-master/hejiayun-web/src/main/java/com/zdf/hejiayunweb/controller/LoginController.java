package com.zdf.hejiayunweb.controller;

import com.zdf.hejiayunweb.service.impl.LoginServiceImpl;
import com.zdf.internalcommon.request.LoginRequestDto;
import com.zdf.internalcommon.response.GetMenuResponseDto;
import com.zdf.internalcommon.response.GetUserInfoResponseDto;
import com.zdf.internalcommon.result.ResponseResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @GetMapping("/getUseInfo/{userId}")
    public ResponseResult<GetUserInfoResponseDto> getUseInfo(@NotNull @PathVariable("userId") Long userId) {
        return loginService.getUserInfo(userId);
    }

    @GetMapping("/getMenu/{userId}")
    public ResponseResult<List<GetMenuResponseDto>> getMenu(@NotNull@PathVariable("userId") Long userId ){
        return loginService.getMenu(userId);
    }
}