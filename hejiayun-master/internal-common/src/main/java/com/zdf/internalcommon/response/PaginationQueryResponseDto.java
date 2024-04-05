package com.zdf.internalcommon.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *@Description Pagination query response
 *@Author mrzhang
 *@Date 2024/3/31 18:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationQueryResponseDto {
   private Long totalSize;
   private Integer pageSize;
   private List<HjyCommunityResponseDto> hjyCommunityEntityList;
}