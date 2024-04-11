package com.zdf.hejiayunsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zdf.hejiayunsystem.mapper.SysAreaMapper;
import com.zdf.hejiayunsystem.service.SysAreaService;
import com.zdf.internalcommon.constant.CommonConstant;
import com.zdf.internalcommon.constant.StatusCode;
import com.zdf.internalcommon.entity.SysAreaEntity;
import com.zdf.internalcommon.response.SysAreaResponseDto;
import com.zdf.internalcommon.result.ResponseResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author mrzhang
* @description 针对表【sys_area(区域表)】的数据库操作Service实现
* @createDate 2024-04-02 17:28:38
*/
@Service
public class SysAreaServiceImpl extends ServiceImpl<SysAreaMapper, SysAreaEntity>
    implements SysAreaService {
    @Resource
    private SysAreaMapper sysAreaMapper;

    @Override
    public ResponseResult<List<SysAreaResponseDto>> queryArea(){
        List<SysAreaEntity> sysAreaEntities = sysAreaMapper.quetyAllArea();
        if (Objects.isNull(sysAreaEntities) || sysAreaEntities.isEmpty()){
            return ResponseResult.fail(StatusCode.AREA_TABLE_IS_EMPTY.getCode(), StatusCode.AREA_TABLE_IS_EMPTY.getMessage(), Collections.emptyList());
        }
        List<SysAreaResponseDto> sysAreaResponseDtoList = sysAreaEntities.stream()
                .filter(sysAreaEntity -> Objects.equals(sysAreaEntity.getParentId(), CommonConstant.ZERO))
                .map(sysAreaEntity -> {
                    SysAreaResponseDto sysAreaResponseDto = new SysAreaResponseDto();
                    sysAreaResponseDto.setName(sysAreaEntity.getName());
                    sysAreaResponseDto.setCode(sysAreaEntity.getCode());
                    sysAreaResponseDto.setChildren(getChild(sysAreaResponseDto, sysAreaEntities));
                    return sysAreaResponseDto;
                }).collect(Collectors.toList());
        return ResponseResult.success(sysAreaResponseDtoList);
    }

    private List<SysAreaResponseDto> getChild(SysAreaResponseDto sysAreaResponseDto, List<SysAreaEntity> sysAreaEntities){
        List<SysAreaEntity> sysAreaLists = sysAreaEntities.stream().filter(sysAreaEntity -> Objects.equals(sysAreaEntity.getParentId(), sysAreaResponseDto.getCode()))
                .collect(Collectors.toList());
        if (!sysAreaLists.isEmpty()){
            return sysAreaLists.stream().map(sysArea->{
                SysAreaResponseDto areaResponseDto = new SysAreaResponseDto();
                areaResponseDto.setName(sysArea.getName());
                areaResponseDto.setCode(sysArea.getCode());
                areaResponseDto.setChildren(getChild(areaResponseDto, sysAreaEntities));
                return areaResponseDto;
            }).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}