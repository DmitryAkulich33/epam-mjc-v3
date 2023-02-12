package com.epam.service;

import com.epam.domain.User;
import com.epam.model.dto.UserDto;
import com.epam.model.dto.UserToCreate;

import java.util.List;

public interface UserService {
    UserDto createUser(UserToCreate userToCreate);

    List<UserDto> getAllUsers(int pageNumber, int pageSize);

    User getUserByLogin(String login);
}
