package com.epam.service;

import com.epam.model.dto.UserDto;
import com.epam.model.dto.UserToCreate;

import java.util.List;

public interface UserService {
    UserDto createUser(UserToCreate userToCreate);

    List<UserDto> getAllUsers(Integer pageNumber, Integer pageSize);
}
