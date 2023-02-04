package com.epam.hateoas.assembler.impl;

import com.epam.controller.CommentControllerImpl;
import com.epam.hateoas.assembler.CollectionModelAssembler;
import com.epam.model.dto.CommentDto;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.epam.hateoas.CustomLinkRelation.NEXT_PAGE;
import static com.epam.hateoas.CustomLinkRelation.PREVIOUS_PAGE;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CommentCollectionAssembler implements CollectionModelAssembler<CommentDto> {
    @Override
    public List<Link> getCollectionLinks(int pageNumber, int pageSize, String sortType, String sortField) {
        List<Link> links = new ArrayList<>();
        if (pageNumber > 1) {
            links.add(linkTo(methodOn(CommentControllerImpl.class)
                    .getAllComments(pageNumber - 1, pageSize, sortType, sortField))
                    .withRel(PREVIOUS_PAGE));
        }
        links.add(linkTo(methodOn(CommentControllerImpl.class)
                .getAllComments(pageNumber, pageSize, sortType, sortField))
                .withSelfRel());
        links.add(linkTo(methodOn(CommentControllerImpl.class)
                .getAllComments(pageNumber + 1, pageSize, sortType, sortField))
                .withRel(NEXT_PAGE));

        return links;
    }
}
