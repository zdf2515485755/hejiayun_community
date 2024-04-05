package com.zdf.internalcommon.response;

import lombok.Data;

import java.util.List;

/**
 *@Description SysDept response
 *@Author mrzhang
 *@Date 2024/4/4 19:46
 */
@Data
public class SysDeptResponseDto {
    private Long deptId;
    private String deptName;
    private List<SysDeptResponseDto> children;
}
