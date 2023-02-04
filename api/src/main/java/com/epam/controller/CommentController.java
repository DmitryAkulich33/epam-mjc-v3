package com.epam.controller;

import com.epam.model.dto.CommentDto;
import com.epam.model.dto.CommentToCreate;
import com.epam.model.dto.CommentToUpdate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public interface CommentController {
    ResponseEntity<CollectionModel<CommentDto>> getAllComments(@Positive Integer pageNumber,
                                                               @Positive Integer pageSize,
                                                               @Pattern(regexp = "ASC|DESC") String sortType,
                                                               @Pattern(regexp = "created|modified") String sortField);

    ResponseEntity<CommentDto> getCommentById(@Positive Long commentId);

    ResponseEntity<CommentDto> createComment(@Valid CommentToCreate commentToCreate, @Positive Long newsId);

    ResponseEntity<CommentDto> deleteCommentById(@Positive Long commentId);

    ResponseEntity<CommentDto> updateComment(@Valid CommentToUpdate commentToCreate, @Positive Long commentId);
}
