package com.zdf.hejiayunsystem.controller;

import com.zdf.hejiayunsystem.service.impl.SysAreaServiceImpl;
import com.zdf.internalcommon.response.SysAreaResponseDto;
import com.zdf.internalcommon.result.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 *@Description 类功能简要描述
 *@Author mrzhang
 *@Date 2024/4/2 18:30
 */
@RestController
@RequestMapping("/area")
public class SysAreaCtroller {
    @Resource
    private SysAreaServiceImpl sysAreaServiceImpl;

    @GetMapping("/queryArea")
    public ResponseResult<List<SysAreaResponseDto>> queryArea(){
        return sysAreaServiceImpl.queryArea();
    }
}
