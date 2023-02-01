package com.epam.model.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class NewsToUpdate {
    @Pattern(regexp = "^([A-Z]|[0-9]).{4,29}$")
    private String title;

    @Pattern(regexp = "^([A-Z]|[0-9]).{4,254}$")
    private String content;

    private List<TagToCreate> tags;
}
