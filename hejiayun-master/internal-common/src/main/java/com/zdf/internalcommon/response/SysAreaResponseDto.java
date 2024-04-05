package com.zdf.internalcommon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *@Description SysArea response
 *@Author mrzhang
 *@Date 2024/4/2 19:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysAreaResponseDto {
    private Integer code;
    private String name;
    private List<SysAreaResponseDto> children;
}