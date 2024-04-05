package com.zdf.hejiayunsystem.controller;

import com.zdf.hejiayunsystem.service.impl.SysDeptServiceImpl;
import com.zdf.internalcommon.response.SysDeptResponseDto;
import com.zdf.internalcommon.result.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 *@Description API for operating Dept
 *@Author mrzhang
 *@Date 2024/4/4 19:48
 */
@RestController
@RequestMapping("/dept")
public class SysDeptController {
    @Resource
    private SysDeptServiceImpl sysDeptService;

    @GetMapping("/queryDept")
    public ResponseResult<List<SysDeptResponseDto>> queryDept(@NotBlank @RequestParam("delFlag") String delFlag){
        return sysDeptService.queryDept(delFlag);
    }
}