package com.zdf.internalcommon.result;

import lombok.Builder;
import lombok.Data;

/**
 *@Description Packaging token result
 *@Author mrzhang
 *@Date 2024/3/25 02:54
 */
@Data
@Builder
public class TokenResult {
    private String userName;
    private String userPassword;
}
