package com.epam.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class AuthorToCreate {
    @NotBlank
    @Pattern(regexp = "^\\S{3,15}$")
    private String name;
}
