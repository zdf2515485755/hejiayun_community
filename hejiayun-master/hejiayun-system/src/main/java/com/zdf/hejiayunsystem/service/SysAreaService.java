package com.zdf.hejiayunsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zdf.internalcommon.entity.SysAreaEntity;
import com.zdf.internalcommon.response.SysAreaResponseDto;
import com.zdf.internalcommon.result.ResponseResult;

import java.util.List;

/**
* @author mrzhang
* @description 针对表【sys_area(区域表)】的数据库操作Service
* @createDate 2024-04-02 17:28:38
*/
public interface SysAreaService extends IService<SysAreaEntity> {
    /**
     * @return ResponseResult<List<SysAreaResponseDto>>
     * @author mrzhang
     * @description Query area
     * @date 2024/4/11 18:10
     */
    ResponseResult<List<SysAreaResponseDto>> queryArea();
}