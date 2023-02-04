package com.epam.hateoas.assembler.impl;

import com.epam.controller.AuthorControllerImpl;
import com.epam.hateoas.assembler.CollectionModelAssembler;
import com.epam.model.dto.AuthorDto;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.epam.hateoas.CustomLinkRelation.NEXT_PAGE;
import static com.epam.hateoas.CustomLinkRelation.PREVIOUS_PAGE;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AuthorCollectionAssembler implements CollectionModelAssembler<AuthorDto> {
    @Override
    public List<Link> getCollectionLinks(int pageNumber, int pageSize) {
        List<Link> links = new ArrayList<>();
        if (pageNumber > 1) {
            links.add(linkTo(methodOn(AuthorControllerImpl.class).getAllAuthors(pageNumber - 1, pageSize))
                    .withRel(PREVIOUS_PAGE));
        }
        links.add(linkTo(methodOn(AuthorControllerImpl.class).getAllAuthors(pageNumber, pageSize)).withSelfRel());
        links.add(linkTo(methodOn(AuthorControllerImpl.class).getAllAuthors(pageNumber + 1, pageSize))
                .withRel(NEXT_PAGE));

        return links;
    }
}
