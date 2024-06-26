package com.zdf.hejiayunweb.controller;

import com.zdf.hejiayunweb.service.impl.HjyCommunityServiceImpl;
import com.zdf.internalcommon.entity.HjyCommunityEntity;
import com.zdf.internalcommon.request.InsertCommunityRequestDto;
import com.zdf.internalcommon.request.PaginationQueryCommunityRequestDto;
import com.zdf.internalcommon.request.PaginationQueryRequestDto;
import com.zdf.internalcommon.request.UpdateCommunityRequestDto;
import com.zdf.internalcommon.response.PaginationQueryResponseDto;
import com.zdf.internalcommon.result.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *@Description API for operating community
 *@Author mrzhang
 *@Date 2024/3/31 03:37
 */
@RestController
@RequestMapping("/community")
public class HjyCommunityController {

    @Resource
    private HjyCommunityServiceImpl hjyCommunityService;

    @GetMapping("/paginationQueryCommunity")
    public ResponseResult<PaginationQueryResponseDto> paginationQueryCommunity(@RequestBody PaginationQueryCommunityRequestDto paginationQueryCommunityRequestDto){
        return hjyCommunityService.paginationQueryCommunity(paginationQueryCommunityRequestDto);
    }
    @PostMapping("/insertCommunity")
    public ResponseResult<Integer> insertCommunity(@Validated @RequestBody InsertCommunityRequestDto insertCommunityRequestDto){
        return hjyCommunityService.insertCommunity(insertCommunityRequestDto);
    }
    @PostMapping("/updateCommunity")
    public ResponseResult<Integer> updateCommunity(@Validated @RequestBody UpdateCommunityRequestDto updateCommunityRequestDto){
        return hjyCommunityService.updateCommunity(updateCommunityRequestDto);
    }

    @GetMapping("/deleteCommunity")
    public ResponseResult<Integer> deleteCommunity(@RequestParam("communityIdArray") Long[] communityIdArray){
        return hjyCommunityService.deleteCommunity(communityIdArray);
    }

    @GetMapping("/downloadExcel")
    public void downloadExcel(@Validated @RequestBody PaginationQueryRequestDto paginationQueryRequestDto, HttpServletResponse httpServletResponse){
        hjyCommunityService.downloadExcel(paginationQueryRequestDto, httpServletResponse);
    }
    @PreAuthorize("hasAuthority('system:user:list')")
    @GetMapping("/dropDownList")
    public ResponseResult<List<HjyCommunityEntity>> dropDownList(){
        return hjyCommunityService.dropDownList();
    }
}