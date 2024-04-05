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
    INSERT_DATA_ERROR(10002, "INSERT DATA ERROR"),
    COMMUNITY_IS_NOT_EXIT(10003, "COMMUNITY IS NOT EXIT"),
    UPDATE_COMMUNITY_ERROR(10004, "UPDATE COMMUNITY ERROR"),
    DELETE_COMMUNITY_ERROR(10005, "DELETE COMMUNITY ERROR"),
    COMMUNITY_Id_IS_EMPTY(10006, "COMMUNITY Id IS EMPTY");

    private final int code;
    private final String message;

    StatusCode(int code, String message){
        this.code = code;
        this.message = message;
    }
}
