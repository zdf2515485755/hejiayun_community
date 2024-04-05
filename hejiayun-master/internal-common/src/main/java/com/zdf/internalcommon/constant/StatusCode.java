package com.zdf.internalcommon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author mrzhang
 * @description Status code
 * @date 2024/3/16 19:43
 */
@Getter
@AllArgsConstructor
public enum StatusCode {
    //成功
    SUCCESS(1, "SUCCESS"),
    FAIL(0, "FAIL"),
    AREA_TABLE_IS_EMPTY(10001, "AREA TABLE IS EMPTY"),
    INSERT_DATA_ERROR(10002, "INSERT DATA ERROR"),
    COMMUNITY_IS_NOT_EXIT(10003, "COMMUNITY IS NOT EXIT"),
    UPDATE_COMMUNITY_ERROR(10004, "UPDATE COMMUNITY ERROR"),
    DELETE_COMMUNITY_ERROR(10005, "DELETE COMMUNITY ERROR"),
    COMMUNITY_NUMBER_IS_EMPTY(10006, "COMMUNITY Id IS EMPTY"),
    DEL_FLAG_FORMAT_IS_ERROR(10003, "DEL FLAG FORMAT IS ERROR"),
    DEPT_TABLE_IS_EMPTY(10004, "DEPT TABLE IS EMPTY");

    private final int code;
    private final String message;
}