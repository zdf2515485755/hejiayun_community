package com.zdf.hejiayunweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.zdf.hejiayunweb.mapper.HjyCommunityMapper;
import com.zdf.hejiayunweb.service.HjyCommunityService;
import com.zdf.internalcommon.constant.HjyCommunityConstant;
import com.zdf.internalcommon.constant.StatusCode;
import com.zdf.internalcommon.entity.HjyCommunityEntity;
import com.zdf.internalcommon.request.InsertCommunityRequestDto;
import com.zdf.internalcommon.request.PaginationQueryCommunityRequestDto;
import com.zdf.internalcommon.request.UpdateCommunityRequestDto;
import com.zdf.internalcommon.response.HjyCommunityResponseDto;
import com.zdf.internalcommon.response.PaginationQueryResponseDto;
import com.zdf.internalcommon.result.ResponseResult;
import com.zdf.internalcommon.util.JpaUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
* @author mrzhang
* @description 针对表【hjy_community(小区 )】的数据库操作Service实现
* @createDate 2024-03-31 01:33:13
*/
@Service
public class HjyCommunityServiceImpl extends ServiceImpl<HjyCommunityMapper, HjyCommunityEntity>
    implements HjyCommunityService {
    @Resource
    private HjyCommunityMapper hjyCommunityMapper;
    public ResponseResult<PaginationQueryResponseDto> paginationQueryCommunity(PaginationQueryCommunityRequestDto paginationQueryCommunityRequestDto){
        PageMethod.startPage(paginationQueryCommunityRequestDto.getPageNum(), paginationQueryCommunityRequestDto.getPageSize());
        List<HjyCommunityResponseDto> paginationQueryList = hjyCommunityMapper.paginationQueryCommunity(paginationQueryCommunityRequestDto.getCommunityName(), paginationQueryCommunityRequestDto.getCommunityCode());
        PageInfo<HjyCommunityResponseDto>pageInfo = PageInfo.of(paginationQueryList);
        long totalSize = pageInfo.getTotal();
        int pageSize = pageInfo.getPageSize();
        PaginationQueryResponseDto paginationQueryResponseDto = PaginationQueryResponseDto.builder()
                .pageSize(pageSize)
                .totalSize(totalSize)
                .hjyCommunityEntityList(paginationQueryList)
                .build();
        return ResponseResult.success(paginationQueryResponseDto);
    }

    public ResponseResult<Integer> insertCommunity(InsertCommunityRequestDto insertCommunityRequestDto){
        HjyCommunityEntity hjyCommunityEntity = new HjyCommunityEntity();
        BeanUtils.copyProperties(insertCommunityRequestDto, hjyCommunityEntity);
        hjyCommunityEntity.setCommunityCode(HjyCommunityConstant.COMMUNITY_CODE_PREFIX + LocalDateTime.now());
        int count = hjyCommunityMapper.insert(hjyCommunityEntity);
        return count > 0 ? ResponseResult.success(count):ResponseResult.fail(StatusCode.INSERT_DATA_ERROR.getCode(), StatusCode.INSERT_DATA_ERROR.getMessage(), count);
    }

    public ResponseResult<Integer> updateCommunity(UpdateCommunityRequestDto updateCommunityRequestDto){
        HjyCommunityEntity hjyCommunityEntity = hjyCommunityMapper.selectById(updateCommunityRequestDto.getCommunityId());
        if (Objects.isNull(hjyCommunityEntity)){
            return ResponseResult.fail(StatusCode.COMMUNITY_IS_NOT_EXIT.getCode(), StatusCode.COMMUNITY_IS_NOT_EXIT.getMessage(), HjyCommunityConstant.ZERO);
        }
        JpaUtil.copyNotNullProperties(updateCommunityRequestDto, hjyCommunityEntity);
        int count = hjyCommunityMapper.updateById(hjyCommunityEntity);
        if (count <= HjyCommunityConstant.ZERO){
            return ResponseResult.fail(StatusCode.UPDATE_COMMUNITY_ERROR.getCode(), StatusCode.UPDATE_COMMUNITY_ERROR.getMessage(), HjyCommunityConstant.ZERO);
        }
        return ResponseResult.success(count);
    }

    public ResponseResult<Integer> deleteCommunity(Long[] communityIdArray){
        if (Objects.isNull(communityIdArray) || communityIdArray.length == HjyCommunityConstant.ZERO){
            return ResponseResult.fail(StatusCode.COMMUNITY_NUMBER_IS_EMPTY.getCode(), StatusCode.COMMUNITY_NUMBER_IS_EMPTY.getMessage());
        }
        int count = hjyCommunityMapper.deleteBatchIds(Arrays.asList(communityIdArray));
        return count > 0 ? ResponseResult.success(count) : ResponseResult.fail(StatusCode.DELETE_COMMUNITY_ERROR.getCode(), StatusCode.DELETE_COMMUNITY_ERROR.getMessage(), count);
    }

}




