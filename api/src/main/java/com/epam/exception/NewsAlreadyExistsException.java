package com.epam.exception;

public class NewsAlreadyExistsException extends ServiceException {
    public NewsAlreadyExistsException(String s, Object... args) {
        super(s, args);
    }
}
