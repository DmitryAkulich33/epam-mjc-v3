package com.epam.controller;

import com.epam.model.dto.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

public interface NewsController {
    ResponseEntity<CollectionModel<NewsDto>> getAllNews(@Positive Integer pageNumber,
                                                        @Positive Integer pageSize,
                                                        @Pattern(regexp = "ASC|DESC") String sortType,
                                                        @Pattern(regexp = "created|modified") String sortField
    );

    ResponseEntity<NewsDto> getNewsById(@Positive Long newsId);

    ResponseEntity<CollectionModel<TagDto>> getNewsTags(@Positive Long newsId);

    ResponseEntity<AuthorDto> getNewsAuthor(@Positive Long newsId);

    ResponseEntity<CollectionModel<CommentDto>> getNewsComments(@Positive Long newsId);

    ResponseEntity<NewsDto> createNews(@Valid NewsToCreate newsToCreate, @Positive Long authorId);

    ResponseEntity<NewsDto> deleteNewsById(@Positive Long newsId);

    ResponseEntity<NewsDto> updateNews(@Valid NewsToUpdate newsToUpdate, @Positive Long newsId);
}
