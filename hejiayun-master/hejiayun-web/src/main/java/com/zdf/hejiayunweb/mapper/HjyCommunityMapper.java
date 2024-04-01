package com.zdf.hejiayunweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zdf.internalcommon.entity.HjyCommunityEntity;
import com.zdf.internalcommon.response.HjyCommunityDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author mrzhang
* @description 针对表【hjy_community(小区 )】的数据库操作Mapper
* @createDate 2024-03-31 01:33:13
* @Entity generator.domain.HjyCommunity
*/
@Mapper
public interface HjyCommunityMapper extends BaseMapper<HjyCommunityEntity> {
    /**
     * @param communityName: 
     * @param communityCode:
     * @return List
     * @author mrzhang
     * @description Pagination query community
     * @date 2024/4/1 03:50
     */
    List<HjyCommunityDto> paginationQueryCommunity(@Param("communityName") String communityName, @Param("communityCode") String communityCode);
}




