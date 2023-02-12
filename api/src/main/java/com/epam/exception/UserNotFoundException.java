package com.epam.exception;

public class UserNotFoundException extends ServiceException{
    public UserNotFoundException(String s, Object... args) {
        super(s, args);
    }
}
