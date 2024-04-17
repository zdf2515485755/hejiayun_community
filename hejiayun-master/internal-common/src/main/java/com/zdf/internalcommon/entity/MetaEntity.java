package com.zdf.internalcommon.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@Description 类功能简要描述
 *@Author mrzhang
 *@Date 2024/4/17 22:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaEntity {
    private String title;
    private String icon;
    private boolean noCache;
}