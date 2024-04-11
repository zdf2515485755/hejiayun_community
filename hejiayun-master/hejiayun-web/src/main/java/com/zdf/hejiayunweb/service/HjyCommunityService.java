package com.zdf.hejiayunweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zdf.internalcommon.entity.HjyCommunityEntity;
import com.zdf.internalcommon.request.InsertCommunityRequestDto;
import com.zdf.internalcommon.request.PaginationQueryCommunityRequestDto;
import com.zdf.internalcommon.request.PaginationQueryRequestDto;
import com.zdf.internalcommon.request.UpdateCommunityRequestDto;
import com.zdf.internalcommon.response.PaginationQueryResponseDto;
import com.zdf.internalcommon.result.ResponseResult;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* @author mrzhang
* @description 针对表【hjy_community(小区 )】的数据库操作Service
* @createDate 2024-03-31 01:33:13
*/
public interface HjyCommunityService extends IService<HjyCommunityEntity> {
    /**
     * @param paginationQueryCommunityRequestDto:
     * @return ResponseResult<PaginationQueryResponseDto>
     * @author mrzhang
     * @description pagination query community
     * @date 2024/4/11 02:32
     */
    ResponseResult<PaginationQueryResponseDto> paginationQueryCommunity(PaginationQueryCommunityRequestDto paginationQueryCommunityRequestDto);
    /**
     * @param insertCommunityRequestDto:
     * @return ResponseResult<Integer>
     * @author mrzhang
     * @description insert community
     * @date 2024/4/11 02:33
     */
    ResponseResult<Integer> insertCommunity(InsertCommunityRequestDto insertCommunityRequestDto);
    /**
     * @param updateCommunityRequestDto:
     * @return ResponseResult<Integer>
     * @author mrzhang
     * @description update community
     * @date 2024/4/11 02:33
     */
    ResponseResult<Integer> updateCommunity(UpdateCommunityRequestDto updateCommunityRequestDto);
    /**
     * @param communityIdArray:
     * @return ResponseResult<Integer>
     * @author mrzhang
     * @description delete community
     * @date 2024/4/11 02:34
     */
    ResponseResult<Integer> deleteCommunity(Long[] communityIdArray);
    /**
     * @param paginationQueryRequestDto:
     * @param httpServletResponse:
     * @author mrzhang
     * @description download excel
     * @date 2024/4/11 02:34
     */
    void downloadExcel(PaginationQueryRequestDto paginationQueryRequestDto, HttpServletResponse httpServletResponse);
    /**
     * @return ResponseResult<List<HjyCommunityEntity>>
     * @author mrzhang
     * @description drop down list
     * @date 2024/4/11 02:34
     */
    ResponseResult<List<HjyCommunityEntity>> dropDownList();

}