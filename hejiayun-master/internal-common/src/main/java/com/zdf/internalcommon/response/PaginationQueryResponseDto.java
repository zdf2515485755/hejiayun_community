package com.zdf.internalcommon.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 *@Description Pagination query response
 *@Author mrzhang
 *@Date 2024/3/31 18:36
 */
@Data
@Builder
public class PaginationQueryResponseDto {
   private Long totalSize;
   private Integer pageSize;
   private List<HjyCommunityDto> hjyCommunityEntityList;
}