package com.epam.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class RoleDto {
    private Long id;
    private String name;
}
