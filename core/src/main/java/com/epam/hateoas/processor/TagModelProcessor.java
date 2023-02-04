package com.epam.hateoas.processor;

import com.epam.controller.TagControllerImpl;
import com.epam.model.dto.TagDto;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import static com.epam.hateoas.CustomLinkRelation.DELETE_BY_ID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TagModelProcessor implements RepresentationModelProcessor<TagDto> {
    @Override
    public TagDto process(TagDto model) {
        model.add(linkTo(methodOn(TagControllerImpl.class).getTagById(model.getId()))
                .withSelfRel());
        model.add(linkTo(methodOn(TagControllerImpl.class).deleteTagById(model.getId()))
                .withRel(DELETE_BY_ID));

        return model;
    }
}
