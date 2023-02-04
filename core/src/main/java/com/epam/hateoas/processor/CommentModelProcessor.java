package com.epam.hateoas.processor;

import com.epam.controller.CommentControllerImpl;
import com.epam.model.dto.CommentDto;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import static com.epam.hateoas.CustomLinkRelation.DELETE_BY_ID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CommentModelProcessor implements RepresentationModelProcessor<CommentDto> {
    @Override
    public CommentDto process(CommentDto model) {
        Long id = model.getId();
        model.add(linkTo(methodOn(CommentControllerImpl.class).getCommentById(id)).withSelfRel());
        model.add(linkTo(methodOn(CommentControllerImpl.class).deleteCommentById(id)).withRel(DELETE_BY_ID));

        return model;
    }
}
