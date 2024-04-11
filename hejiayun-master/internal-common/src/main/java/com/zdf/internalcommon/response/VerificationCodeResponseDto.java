package com.zdf.internalcommon.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@Description verification code response
 *@Author mrzhang
 *@Date 2024/4/11 02:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationCodeResponseDto {
    private String uuid;
    private String image;
}
