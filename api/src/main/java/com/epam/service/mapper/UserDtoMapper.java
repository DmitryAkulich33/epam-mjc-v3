package com.epam.service.mapper;

import com.epam.domain.User;
import com.epam.model.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleDtoMapper.class})
public interface UserDtoMapper {
    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> user);
}
