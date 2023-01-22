package com.epam.exception;

public class TagNotFoundException extends ServiceException {
    public TagNotFoundException(String s, Object... args) {
        super(s, args);
    }
}
