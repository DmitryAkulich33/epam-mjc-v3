package com.epam.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Data
public class AuthorDto extends RepresentationModel<AuthorDto> {
    private Long id;
    private String name;
}
