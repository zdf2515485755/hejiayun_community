package com.zdf.internalcommon.response;

import lombok.Data;

import java.util.List;

/**
 *@Description 类功能简要描述
 *@Author mrzhang
 *@Date 2024/4/2 19:15
 */
@Data
public class SysAreaResponseDto {
    private Integer code;
    private String name;
    private List<SysAreaResponseDto> children;
}
