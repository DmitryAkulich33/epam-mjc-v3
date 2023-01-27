package com.epam.exception;

public class AuthorAlreadyExistsException extends ServiceException {
    public AuthorAlreadyExistsException(String s, Object... args) {
        super(s, args);
    }
}
