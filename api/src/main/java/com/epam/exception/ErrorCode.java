package com.epam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    TAG_DAO_ERROR_CODE("01"),
    TAG_SERVICE_ERROR_CODE("02"),
    AUTHOR_DAO_ERROR_CODE("03"),
    AUTHOR_SERVICE_ERROR_CODE("04"),
    VALIDATION_ERROR_CODE("05"),
    SERVER_ERROR_CODE("06"),
    REQUEST_ERROR_CODE("07");

    private final String errorCode;
}
