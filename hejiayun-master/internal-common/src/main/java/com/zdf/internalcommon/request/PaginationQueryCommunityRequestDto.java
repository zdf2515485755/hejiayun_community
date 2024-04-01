package com.zdf.internalcommon.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *@Description Pagination query community request
 *@Author mrzhang
 *@Date 2024/3/31 03:49
 */
@Data
public class PaginationQueryCommunityRequestDto {
    /**
     * 小区名称
     */
    private String communityName;
    /**
     * 小区编码
     */
    private String communityCode;
    @NotNull(message = "page size can not be null ")
    private Integer pageSize;
    @NotNull(message = "page num can not be null ")
    private  Integer pageNum;
}
