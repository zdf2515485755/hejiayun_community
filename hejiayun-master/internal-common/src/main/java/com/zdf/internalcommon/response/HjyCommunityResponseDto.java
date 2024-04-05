package com.zdf.internalcommon.response;

import com.zdf.internalcommon.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *@Description Hjy community response
 *@Author mrzhang
 *@Date 2024/4/1 22:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HjyCommunityResponseDto extends BaseEntity {

    private Long communityId;
    private String communityName;
    private String communityCode;
    private String communityProvinceCode;
    private String communityProvinceName;
    private String communityCityCode;
    private String communityCityName;
    private String communityTownCode;
    private String communityTownName;
    private String communityDetailedAddress;
    private String communityLongitude;
    private String communityLatitude;
    private Long deptId;
    private Integer communitySort;
}