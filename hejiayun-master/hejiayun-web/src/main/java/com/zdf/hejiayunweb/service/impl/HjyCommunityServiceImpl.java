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
import com.zdf.internalcommon.response.HjyCommunityDto;
import com.zdf.internalcommon.response.PaginationQueryResponseDto;
import com.zdf.internalcommon.result.ResponseResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

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
        List<HjyCommunityDto> paginationQueryList = hjyCommunityMapper.paginationQueryCommunity(paginationQueryCommunityRequestDto.getCommunityName(), paginationQueryCommunityRequestDto.getCommunityCode());
        PageInfo<HjyCommunityDto>pageInfo = PageInfo.of(paginationQueryList);
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
        return count > 0 ? ResponseResult.success(count):ResponseResult.fail(StatusCode.INSRT_DATA_ERROR.getCode(), StatusCode.INSRT_DATA_ERROR.getMessage(), count);
    }

}




