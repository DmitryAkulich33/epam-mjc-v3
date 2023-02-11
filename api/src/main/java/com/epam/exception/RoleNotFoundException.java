package com.epam.exception;

public class RoleNotFoundException extends ServiceException{
    public RoleNotFoundException(String s, Object... args) {
        super(s, args);
    }
}
