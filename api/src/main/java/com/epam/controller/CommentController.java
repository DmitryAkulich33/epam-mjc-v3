package com.epam.controller;

import com.epam.model.dto.CommentDto;
import com.epam.model.dto.CommentToCreate;
import com.epam.model.dto.CommentToUpdate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public interface CommentController extends BaseEntityController<CommentDto> {
    ResponseEntity<CollectionModel<CommentDto>> getAllComments(@Positive int pageNumber,
                                                               @Positive int pageSize,
                                                               @Pattern(regexp = "ASC|DESC") String sortType,
                                                               @Pattern(regexp = "created|modified") String sortField);

    ResponseEntity<CommentDto> createComment(@Valid CommentToCreate commentToCreate, @Positive Long newsId);

    ResponseEntity<CommentDto> updateComment(@Valid CommentToUpdate commentToCreate, @Positive Long commentId);
}
