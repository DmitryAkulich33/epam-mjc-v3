package com.epam.controller;

import com.epam.model.dto.UserDto;
import com.epam.model.dto.UserToCreate;
import com.epam.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserToCreate userToCreate) {
        UserDto userDto = userService.createUser(userToCreate);

        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(defaultValue = "${default.pageNumber}") @Positive Integer pageNumber,
                                                     @RequestParam(defaultValue = "${default.pageSize}") @Positive Integer pageSize) {
        List<UserDto> users = userService.getAllUsers(pageNumber, pageSize);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
