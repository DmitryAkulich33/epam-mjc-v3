package com.epam.hateoas.processor;

import com.epam.controller.NewsControllerImpl;
import com.epam.model.dto.NewsDto;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import static com.epam.hateoas.CustomLinkRelation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class NewsModelProcessor implements RepresentationModelProcessor<NewsDto> {
    private final TagModelProcessor tagModelProcessor;
    private final AuthorModelProcessor authorModelProcessor;
    private final CommentModelProcessor commentModelProcessor;

    @Override
    public NewsDto process(NewsDto model) {
        Long id = model.getId();
        model.add(linkTo(methodOn(NewsControllerImpl.class).getEntityById(id)).withSelfRel());
        model.add(linkTo(methodOn(NewsControllerImpl.class).deleteEntityById(id)).withRel(DELETE_BY_ID));
        model.add(linkTo(methodOn(NewsControllerImpl.class).getNewsComments(id)).withRel(NEWS_COMMENTS));
        model.add(linkTo(methodOn(NewsControllerImpl.class).getNewsAuthor(model.getAuthor().getId())).withRel(NEWS_AUTHOR));
        model.add(linkTo(methodOn(NewsControllerImpl.class).getNewsTags(id)).withRel(NEWS_TAGS));
        model.getTags().forEach(tagModelProcessor::process);
        model.getComments().forEach(commentModelProcessor::process);
        authorModelProcessor.process(model.getAuthor());

        return model;
    }
}
