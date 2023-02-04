package com.epam.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Data
public class TagDto extends RepresentationModel<TagDto> {
    private Long id;
    private String name;
}
