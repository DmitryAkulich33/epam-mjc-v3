package com.epam.model.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CommentToCreate {
    @NotBlank
    @Pattern(regexp = "^\\.{3,255}$")
    private String content;
}
