package com.epam.exception;

public class TagAlreadyExistsException extends ServiceException {
    public TagAlreadyExistsException(String s, Object... args) {
        super(s, args);
    }
}
