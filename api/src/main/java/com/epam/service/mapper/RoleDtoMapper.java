package com.epam.service.mapper;

import com.epam.domain.Role;
import com.epam.model.dto.RoleDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleDtoMapper {
    RoleDto toRoleDto(Role role);

    List<RoleDto> toRoleDtoList(List<Role> roles);
}
