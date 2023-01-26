package com.epam.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class TagToCreate {
    @NotBlank
    @Pattern(regexp = "^\\S{1,70}$")
    private String name;
}
