package com.zdf.internalcommon.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *@Description 类功能简要描述
 *@Author mrzhang
 *@Date 2024/4/12 00:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @NotBlank(message = "user name can not be empty")
    @Size(min = 5, max = 10, message = "user name length must be 5")
    private String userName;
    @NotBlank(message = "password can not be empty")
    private String password;
    private String uuid;
    @NotBlank(message = "verification code can not be empty")
    @Size(min = 5, max = 5, message = "Verification code length must be 5")
    private String verificationCode;
}
