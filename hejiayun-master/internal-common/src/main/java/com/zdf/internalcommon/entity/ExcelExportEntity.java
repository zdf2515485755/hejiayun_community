package com.zdf.internalcommon.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

/**
 *@Description 类功能简要描述
 *@Author mrzhang
 *@Date 2024/4/5 19:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelExportEntity extends BaseEntity {
    @ExcelProperty(value = "小区名称", index = 0)
    private String communityName;
    @ExcelProperty(value = "小区编码", index = 1)
    private String communityCode;
    @ExcelProperty(value = "省", index = 2)
    private String communityProvinceName;
    @ExcelProperty(value = "市", index = 3)
    private String communityCityName;
    @ExcelProperty(value = "区/县", index = 4)
    private String communityTownName;
}