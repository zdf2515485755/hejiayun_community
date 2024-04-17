package com.zdf.internalcommon.response;

import com.zdf.internalcommon.entity.MetaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *@Description Get menu response dto
 *@Author mrzhang
 *@Date 2024/4/17 22:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMenuResponseDto {
    private String name;
    private String path;
    private boolean hidden;
    private String redirect;
    private String component;
    private boolean alwaysShow;
    private MetaEntity metaEntity;
    private List<GetMenuResponseDto> children;
}
