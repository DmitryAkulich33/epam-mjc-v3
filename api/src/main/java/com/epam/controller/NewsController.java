package com.epam.controller;

import com.epam.model.dto.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

public interface NewsController {
    ResponseEntity<List<NewsDto>> getAllNews(@Positive int pageNumber,
                                             @Positive int pageSize,
                                             @Pattern(regexp = "ASC|DESC") String sortType,
                                             @Pattern(regexp = "created|modified") String sortField
    );

    ResponseEntity<NewsDto> getNewsById(@Positive Long newsId);

    ResponseEntity<List<TagDto>> getNewsTags(@Positive Long newsId);

    ResponseEntity<AuthorDto> getNewsAuthor(@Positive Long newsId);

    ResponseEntity<List<CommentDto>> getNewsComments(@Positive Long newsId);

    ResponseEntity<NewsDto> createNews(@Valid NewsToCreate newsToCreate, @Positive Long authorId);

    void deleteNewsById(@Positive Long newsId);

    ResponseEntity<NewsDto> updateNews(@Valid NewsToUpdate newsToUpdate, @Positive Long newsId);
}
