package com.epam.controller;

import com.epam.model.dto.JwtRequest;
import com.epam.model.dto.JwtResponse;
import com.epam.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/authentication")
public class AuthenticationControllerImpl implements AuthenticationController {
    private final AuthenticationService authenticationService;

    @Override
    @PostMapping(path = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtResponse> createToken(@RequestBody @Valid JwtRequest jwtRequest) {
        String token = authenticationService.createToken(jwtRequest.getUsername(), jwtRequest.getPassword());

        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }
}
