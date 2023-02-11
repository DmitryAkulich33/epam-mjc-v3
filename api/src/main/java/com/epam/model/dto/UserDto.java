package com.epam.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class UserDto {
    private Long id;
    private String login;
    private List<RoleDto> roles;
}
