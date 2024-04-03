package com.zdf.hejiayunsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zdf.internalcommon.entity.SysAreaEntity;

import java.util.List;

/**
* @author mrzhang
* @description 针对表【sys_area(区域表)】的数据库操作Mapper
* @createDate 2024-04-02 17:28:38
* @Entity generator.domain.SysArea
*/
public interface SysAreaMapper extends BaseMapper<SysAreaEntity> {
    /**
     * @return List<SysAreaEntity>
     * @author mrzhang
     * @description Quety all area
     * @date 2024/4/2 22:39
     */
    List<SysAreaEntity> quetyAllArea();
}




