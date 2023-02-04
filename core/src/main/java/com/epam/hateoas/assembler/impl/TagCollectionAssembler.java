package com.epam.hateoas.assembler.impl;

import com.epam.controller.TagControllerImpl;
import com.epam.hateoas.assembler.CollectionModelAssembler;
import com.epam.model.dto.TagDto;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.epam.hateoas.CustomLinkRelation.NEXT_PAGE;
import static com.epam.hateoas.CustomLinkRelation.PREVIOUS_PAGE;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TagCollectionAssembler implements CollectionModelAssembler<TagDto> {
    @Override
    public List<Link> getCollectionLinks(int pageNumber, int pageSize, String sortType, String sortField) {
        List<Link> links = new ArrayList<>();
        if (pageNumber > 1) {
            links.add(linkTo(methodOn(TagControllerImpl.class).getAllTags(pageNumber - 1, pageSize))
                    .withRel(PREVIOUS_PAGE));
        }
        links.add(linkTo(methodOn(TagControllerImpl.class).getAllTags(pageNumber, pageSize)).withSelfRel());
        links.add(linkTo(methodOn(TagControllerImpl.class).getAllTags(pageNumber + 1, pageSize))
                .withRel(NEXT_PAGE));

        return links;
    }
}
