package com.epam.exception;

public class AuthenticationDataException extends ServiceException{
    public AuthenticationDataException(String s, Object... args) {
        super(s, args);
    }
}
