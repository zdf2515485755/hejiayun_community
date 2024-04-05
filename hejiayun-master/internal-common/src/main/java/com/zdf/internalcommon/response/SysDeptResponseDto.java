package com.zdf.internalcommon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *@Description SysDept response
 *@Author mrzhang
 *@Date 2024/4/4 19:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysDeptResponseDto {
    private Long deptId;
    private String deptName;
    private List<SysDeptResponseDto> children;
}