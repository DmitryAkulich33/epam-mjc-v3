package com.epam.controller;

import com.epam.model.dto.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface NewsController {
    ResponseEntity<List<NewsDto>> getAllNews(@Positive Integer pageNumber, @Positive Integer pageSize);

    ResponseEntity<NewsDto> getNewsById(@Positive Long newsId);

    ResponseEntity<List<TagDto>> getNewsTags(@Positive Long newsId);

    ResponseEntity<AuthorDto> getNewsAuthor(@Positive Long newsId);

    ResponseEntity<List<CommentDto>> getNewsComments(@Positive Long newsId);

    ResponseEntity<NewsDto> createNews(@Valid NewsToCreate newsToCreate, @Positive Long authorId);

    void deleteNewsById(@Positive Long newsId);

    ResponseEntity<NewsDto> updateNews(@Valid NewsToUpdate newsToUpdate, @Positive Long newsId);
}
