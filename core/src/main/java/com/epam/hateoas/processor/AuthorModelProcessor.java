package com.epam.hateoas.processor;

import com.epam.controller.AuthorControllerImpl;
import com.epam.model.dto.AuthorDto;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import static com.epam.hateoas.CustomLinkRelation.DELETE_BY_ID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AuthorModelProcessor implements RepresentationModelProcessor<AuthorDto> {
    @Override
    public AuthorDto process(AuthorDto model) {
        Long id = model.getId();
        model.add(linkTo(methodOn(AuthorControllerImpl.class).getEntityById(id)).withSelfRel());
        model.add(linkTo(methodOn(AuthorControllerImpl.class).deleteEntityById(id)).withRel(DELETE_BY_ID));

        return model;
    }
}
