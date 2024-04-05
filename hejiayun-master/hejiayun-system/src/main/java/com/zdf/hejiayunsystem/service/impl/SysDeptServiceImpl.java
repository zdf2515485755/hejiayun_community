package com.zdf.hejiayunsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zdf.hejiayunsystem.mapper.SysDeptMapper;
import com.zdf.hejiayunsystem.service.SysDeptService;
import com.zdf.internalcommon.constant.CommonConstant;
import com.zdf.internalcommon.constant.StatusCode;
import com.zdf.internalcommon.entity.SysDeptEntity;
import com.zdf.internalcommon.response.SysDeptResponseDto;
import com.zdf.internalcommon.result.ResponseResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author mrzhang
* @description 针对表【sys_dept(部门表)】的数据库操作Service实现
* @createDate 2024-04-04 19:23:32
*/
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDeptEntity>
    implements SysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    public ResponseResult<List<SysDeptResponseDto>> queryDept(String delFlag){
        try {
            Integer.parseInt(delFlag);
        }catch (NumberFormatException e){
            return ResponseResult.fail(StatusCode.DEL_FLAG_FORMAT_IS_ERROR.getCode(), StatusCode.DEL_FLAG_FORMAT_IS_ERROR.getMessage(), Collections.emptyList());
        }
        List<SysDeptEntity> sysDeptEntities = sysDeptMapper.queryDept(delFlag);
        if (Objects.isNull(sysDeptEntities) || sysDeptEntities.isEmpty()){
            return ResponseResult.fail(StatusCode.DEPT_TABLE_IS_EMPTY.getCode(), StatusCode.DEPT_TABLE_IS_EMPTY.getMessage(), Collections.emptyList());
        }
        List<SysDeptResponseDto> sysDeptResponseDtoList = sysDeptEntities.stream().filter(sysDeptEntity -> Integer.parseInt(sysDeptEntity.getDelFlag()) == CommonConstant.ZERO)
                .map(sysDeptEntity -> {
                    SysDeptResponseDto sysDeptResponseDto = new SysDeptResponseDto();
                    sysDeptResponseDto.setDeptId(sysDeptEntity.getDeptId());
                    sysDeptResponseDto.setDeptName(sysDeptEntity.getDeptName());
                    sysDeptResponseDto.setChildren(getDept(sysDeptResponseDto, sysDeptEntities));
                    return sysDeptResponseDto;
                }).collect(Collectors.toList());
        return ResponseResult.success(sysDeptResponseDtoList);
    }

    private List<SysDeptResponseDto> getDept(SysDeptResponseDto sysDeptResponseDto, List<SysDeptEntity> sysDeptEntities){
        List<SysDeptEntity> deptEntities = sysDeptEntities.stream().filter(sysDept -> Objects.equals(sysDept.getParentId(), sysDeptResponseDto.getDeptId()))
                .collect(Collectors.toList());
        if (!deptEntities.isEmpty()){
            return deptEntities.stream().map(deptEntity->{
                SysDeptResponseDto deptResponseDto = new SysDeptResponseDto();
                deptResponseDto.setDeptId(deptEntity.getDeptId());
                deptResponseDto.setDeptName(deptEntity.getDeptName());
                deptResponseDto.setChildren(getDept(deptResponseDto, sysDeptEntities));
                return deptResponseDto;
            }).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}