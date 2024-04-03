package com.zdf.internalcommon.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zdf.internalcommon.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *@Description 类功能简要描述
 *@Author mrzhang
 *@Date 2024/4/3 20:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateCommunityRequestDto extends BaseEntity {
    /**
     * 小区id
     */
    @TableId(type = IdType.AUTO)
    private Long communityId;

    /**
     * 小区名称
     */
    private String communityName;

    /**
     * 小区编码
     */
    private String communityCode;

    /**
     * 省区划码
     */
    private String communityProvinceCode;

    /**
     * 市区划码
     */
    private String communityCityCode;

    /**
     * 区县区划码
     */
    private String communityTownCode;

    /**
     * 详细地址
     */
    private String communityDetailedAddress;

    /**
     * 经度
     */
    private String communityLongitude;

    /**
     * 纬度
     */
    private String communityLatitude;

    /**
     * 物业id
     */
    private Long deptId;

    /**
     * 排序
     */
    private Integer communitySort;
}