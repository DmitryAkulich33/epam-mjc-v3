package com.epam.exception;

public class NewsNotFoundException extends ServiceException {
    public NewsNotFoundException(String s, Object... args) {
        super(s, args);
    }
}
