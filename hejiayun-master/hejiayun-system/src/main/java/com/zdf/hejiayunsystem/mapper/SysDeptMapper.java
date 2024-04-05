package com.zdf.hejiayunsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zdf.internalcommon.entity.SysDeptEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author mrzhang
* @description 针对表【sys_dept(部门表)】的数据库操作Mapper
* @createDate 2024-04-04 19:23:32
* @Entity generator.domain.SysDept
*/
public interface SysDeptMapper extends BaseMapper<SysDeptEntity> {
    /**
     * @param delFlag:
     * @return List<SysDeptEntity>
     * @author mrzhang
     * @description Query dept by del flag
     * @date 2024/4/4 20:07
     */
    List<SysDeptEntity> queryDept(@Param("delFlag") String delFlag);
}
