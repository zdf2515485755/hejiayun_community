package com.zdf.internalcommon.response;

import com.zdf.internalcommon.entity.SysMenuEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *@Description Menu response dto
 *@Author mrzhang
 *@Date 2024/4/17 23:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponseDto extends SysMenuEntity {
    private List<MenuResponseDto> childrenList;
}
