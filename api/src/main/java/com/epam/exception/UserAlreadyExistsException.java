package com.epam.exception;

public class UserAlreadyExistsException extends ServiceException{
    public UserAlreadyExistsException(String s, Object... args) {
        super(s, args);
    }
}
