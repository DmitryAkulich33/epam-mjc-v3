package com.epam.model.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CommentToCreate {
    @NotBlank
    @Pattern(regexp = "^\\S{3,255}$")
    private String content;
}
