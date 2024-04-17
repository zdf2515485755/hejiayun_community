package com.zdf.internalcommon.constant;

/**
 *@Description 类功能简要描述
 *@Author mrzhang
 *@Date 2024/4/12 02:12
 */
public class RedisConstant {
    public  static final String VERIFY_CODE_KEY_PREFIX = "verify_code:";
    public static final String USER_INFO_KEY_PREFIX = "user_info:";
    public static final Integer EXPIRE_TIME = 60;

    private RedisConstant(){}
}
