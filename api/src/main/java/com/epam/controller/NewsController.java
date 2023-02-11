package com.epam.controller;

import com.epam.model.dto.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public interface NewsController extends BaseEntityController<NewsDto> {
    ResponseEntity<CollectionModel<NewsDto>> getAllNews(@Positive int pageNumber,
                                                        @Positive int pageSize,
                                                        @Pattern(regexp = "ASC|DESC") String sortType,
                                                        @Pattern(regexp = "created|modified") String sortField);

    ResponseEntity<CollectionModel<TagDto>> getNewsTags(@Positive Long newsId);

    ResponseEntity<AuthorDto> getNewsAuthor(@Positive Long newsId);

    ResponseEntity<CollectionModel<CommentDto>> getNewsComments(@Positive Long newsId);

    ResponseEntity<NewsDto> createNews(@Valid NewsToCreate newsToCreate, @Positive Long authorId);

    ResponseEntity<NewsDto> updateNews(@Valid NewsToUpdate newsToUpdate, @Positive Long newsId);
}
