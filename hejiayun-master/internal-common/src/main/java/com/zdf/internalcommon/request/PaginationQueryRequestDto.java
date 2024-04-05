package com.zdf.internalcommon.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *@Description Pagination query request
 *@Author mrzhang
 *@Date 2024/4/5 21:13
 */
@Data
public class PaginationQueryRequestDto {
    @NotNull(message = "page size can not be null ")
    private Integer pageSize;
    @NotNull(message = "page num can not be null ")
    private Integer pageNum;
}