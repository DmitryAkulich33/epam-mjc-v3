package com.epam.controller;

import com.epam.model.dto.UserDto;
import com.epam.model.dto.UserToCreate;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface UserController {
    ResponseEntity<UserDto> createUser(@Valid UserToCreate userToCreate);

    ResponseEntity<List<UserDto>> getAllUsers(@Positive Integer pageNumber, @Positive Integer pageSize);
}
