package com.zdf.internalcommon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 *@Description Get user info response class
 *@Author mrzhang
 *@Date 2024/4/16 21:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserInfoResponseDto {
    private UserInfoDto userInfoDto;
    private Set<String> roleList;
    private Set<String> permissionList;
}
