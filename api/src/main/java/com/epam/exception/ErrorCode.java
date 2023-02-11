package com.epam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    TAG_ERROR_CODE("01"),
    NEWS_ERROR_CODE("02"),
    AUTHOR_ERROR_CODE("03"),
    COMMENT_ERROR_CODE("04"),
    VALIDATION_ERROR_CODE("05"),
    SERVER_ERROR_CODE("06"),
    REQUEST_ERROR_CODE("07"),
    ROLE_ERROR_CODE("08"),
    USER_ERROR_CODE("09");

    private final String errorCode;
}
