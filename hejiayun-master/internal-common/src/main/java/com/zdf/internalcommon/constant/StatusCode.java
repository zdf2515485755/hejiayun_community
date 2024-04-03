package com.zdf.internalcommon.constant;

import lombok.Getter;

/**
 * @author mrzhang
 * @description Status code
 * @date 2024/3/16 19:43
 */
@Getter
public enum StatusCode {
    //成功
    SUCCESS(1, "SUCCESS"),
    FAIL(0, "FAIL"),
    AREA_TABLE_IS_EMPTY(10001, "AREA TABLE IS EMPTY"),
    INSRT_DATA_ERROR(10002, "INSRT DATA ERROR");

    private final int code;
    private final String message;

    StatusCode(int code, String message){
        this.code = code;
        this.message = message;
    }
}
