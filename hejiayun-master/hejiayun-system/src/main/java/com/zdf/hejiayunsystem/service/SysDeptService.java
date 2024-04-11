package com.zdf.hejiayunsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zdf.internalcommon.entity.SysDeptEntity;
import com.zdf.internalcommon.response.SysDeptResponseDto;
import com.zdf.internalcommon.result.ResponseResult;

import java.util.List;

/**
* @author mrzhang
* @description 针对表【sys_dept(部门表)】的数据库操作Service
* @createDate 2024-04-04 19:23:32
*/
public interface SysDeptService extends IService<SysDeptEntity> {
    /**
     * @param delFlag:
     * @return ResponseResult<List<SysDeptResponseDto>>
     * @author mrzhang
     * @description Query dept
     * @date 2024/4/11 18:11
     */
    ResponseResult<List<SysDeptResponseDto>> queryDept(String delFlag);
}