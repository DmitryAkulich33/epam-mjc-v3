package com.epam.controller;

import com.epam.model.dto.JwtRequest;
import com.epam.model.dto.JwtResponse;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

public interface AuthenticationController {
    ResponseEntity<JwtResponse> createToken(@Valid JwtRequest jwtRequest);
}
