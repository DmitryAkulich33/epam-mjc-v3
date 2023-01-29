package com.epam.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class NewsToCreate {
    @NotBlank
    @Pattern(regexp = "^.{5,30}$")
    private String title;

    @NotBlank
    @Pattern(regexp = "^.{5,255}$")
    private String content;

    @NotNull
    @NotEmpty
    private List<TagToCreate> tags;
}
