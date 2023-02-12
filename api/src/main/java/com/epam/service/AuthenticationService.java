package com.epam.service;

public interface AuthenticationService {
    String createToken(String username, String password);
}
