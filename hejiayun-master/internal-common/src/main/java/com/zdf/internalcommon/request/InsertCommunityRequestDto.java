package com.zdf.internalcommon.request;
import com.zdf.internalcommon.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 *@Description 类功能简要描述
 *@Author mrzhang
 *@Date 2024/4/2 02:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InsertCommunityRequestDto extends BaseEntity {
    @NotBlank(message = "communityName can not be empty")
    private String communityName;
    private String communityCode;
    @NotBlank(message = "communityProvinceCode can not be empty")
    private String communityProvinceCode;
    @NotBlank(message = "communityCityCode can not be empty")
    private String communityCityCode;
    @NotBlank(message = "communityTownCode can not be empty")
    private String communityTownCode;
    @NotBlank(message = "ccommunityDetailedAddress can not be empty")
    private String communityDetailedAddress;
    private String communityLongitude;
    private String communityLatitude;
    private Long deptId;
    private Integer communitySort;
}
