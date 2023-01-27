package com.epam.exception;

public class AuthorNotFoundException extends ServiceException {
    public AuthorNotFoundException(String s, Object... args) {
        super(s, args);
    }
}
