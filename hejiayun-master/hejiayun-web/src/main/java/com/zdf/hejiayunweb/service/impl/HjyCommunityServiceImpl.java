package com.zdf.hejiayunweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.zdf.hejiayunweb.mapper.HjyCommunityMapper;
import com.zdf.hejiayunweb.service.HjyCommunityService;
import com.zdf.internalcommon.constant.CommonConstant;
import com.zdf.internalcommon.constant.HjyCommunityConstant;
import com.zdf.internalcommon.constant.StatusCode;
import com.zdf.internalcommon.entity.ExcelExportEntity;
import com.zdf.internalcommon.entity.HjyCommunityEntity;
import com.zdf.internalcommon.request.InsertCommunityRequestDto;
import com.zdf.internalcommon.request.PaginationQueryCommunityRequestDto;
import com.zdf.internalcommon.request.PaginationQueryRequestDto;
import com.zdf.internalcommon.request.UpdateCommunityRequestDto;
import com.zdf.internalcommon.response.HjyCommunityResponseDto;
import com.zdf.internalcommon.response.PaginationQueryResponseDto;
import com.zdf.internalcommon.result.ResponseResult;
import com.zdf.internalcommon.util.ExcelUtil;
import com.zdf.internalcommon.util.JpaUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
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

    @Override
    public ResponseResult<Integer> insertCommunity(InsertCommunityRequestDto insertCommunityRequestDto){
        HjyCommunityEntity hjyCommunityEntity = new HjyCommunityEntity();
        BeanUtils.copyProperties(insertCommunityRequestDto, hjyCommunityEntity);
        hjyCommunityEntity.setCommunityCode(HjyCommunityConstant.COMMUNITY_CODE_PREFIX + LocalDateTime.now());
        int count = hjyCommunityMapper.insert(hjyCommunityEntity);
        return count > 0 ? ResponseResult.success(count):ResponseResult.fail(StatusCode.INSERT_DATA_ERROR.getCode(), StatusCode.INSERT_DATA_ERROR.getMessage(), count);
    }

    @Override
    public ResponseResult<Integer> updateCommunity(UpdateCommunityRequestDto updateCommunityRequestDto){
        HjyCommunityEntity hjyCommunityEntity = hjyCommunityMapper.selectById(updateCommunityRequestDto.getCommunityId());
        if (Objects.isNull(hjyCommunityEntity)){
            return ResponseResult.fail(StatusCode.COMMUNITY_IS_NOT_EXIT.getCode(), StatusCode.COMMUNITY_IS_NOT_EXIT.getMessage(), CommonConstant.ZERO);
        }
        JpaUtil.copyNotNullProperties(updateCommunityRequestDto, hjyCommunityEntity);
        int count = hjyCommunityMapper.updateById(hjyCommunityEntity);
        if (count <= CommonConstant.ZERO){
            return ResponseResult.fail(StatusCode.UPDATE_COMMUNITY_ERROR.getCode(), StatusCode.UPDATE_COMMUNITY_ERROR.getMessage(), CommonConstant.ZERO);
        }
        return ResponseResult.success(count);
    }

    @Override
    public ResponseResult<Integer> deleteCommunity(Long[] communityIdArray){
        if (Objects.isNull(communityIdArray) || communityIdArray.length == CommonConstant.ZERO){
            return ResponseResult.fail(StatusCode.COMMUNITY_NUMBER_IS_EMPTY.getCode(), StatusCode.COMMUNITY_NUMBER_IS_EMPTY.getMessage());
        }
        int count = hjyCommunityMapper.deleteBatchIds(Arrays.asList(communityIdArray));
        return count > 0 ? ResponseResult.success(count) : ResponseResult.fail(StatusCode.DELETE_COMMUNITY_ERROR.getCode(), StatusCode.DELETE_COMMUNITY_ERROR.getMessage(), count);
    }

    @Override
    public void downloadExcel(PaginationQueryRequestDto paginationQueryRequestDto, HttpServletResponse httpServletResponse){
        PageMethod.startPage(paginationQueryRequestDto.getPageNum(), paginationQueryRequestDto.getPageSize());
        List<HjyCommunityResponseDto> paginationQueryList = hjyCommunityMapper.paginationQueryCommunity(null, null);
        List<ExcelExportEntity> excelExportEntities = paginationQueryList.stream().map(hjyCommunityResponse -> {
            ExcelExportEntity excelExportEntity = new ExcelExportEntity();
            excelExportEntity.setCommunityCityName(hjyCommunityResponse.getCommunityCityName());
            excelExportEntity.setCommunityName(hjyCommunityResponse.getCommunityName());
            excelExportEntity.setCommunityProvinceName(hjyCommunityResponse.getCommunityProvinceName());
            excelExportEntity.setCommunityTownName(hjyCommunityResponse.getCommunityTownName());
            excelExportEntity.setCommunityCode(hjyCommunityResponse.getCommunityCode());
            excelExportEntity.setCreateTime(hjyCommunityResponse.getCreateTime());
            excelExportEntity.setRemark(hjyCommunityResponse.getRemark());
            return excelExportEntity;
        }).collect(Collectors.toList());
        ExcelUtil.downloadFile(httpServletResponse, "小区信息表", "小区", excelExportEntities);
    }

    @Override
    public ResponseResult<List<HjyCommunityEntity>> dropDownList(){
        QueryWrapper<HjyCommunityEntity> queryWrapper = new QueryWrapper<>();
        return ResponseResult.success(hjyCommunityMapper.selectList(queryWrapper));
    }

    @PostConstruct
    public void init(){
        QueryWrapper<HjyCommunityEntity> objectQueryWrapper = new QueryWrapper<>();
        List<HjyCommunityEntity> hjyCommunityEntities = hjyCommunityMapper.selectList(objectQueryWrapper);
        hjyCommunityEntities.forEach(
                hjyCommunityEntity -> redisTemplate.opsForValue().set(hjyCommunityEntity.getCommunityName(), hjyCommunityEntity)
        );
    }
}