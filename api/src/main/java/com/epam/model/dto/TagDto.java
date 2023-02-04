package com.epam.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
public class TagDto extends RepresentationModel<TagDto> {
    private Long id;
    private String name;
}
