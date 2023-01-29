package com.epam.controller;

import com.epam.model.dto.CommentDto;
import com.epam.model.dto.CommentToCreate;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface CommentController {
    ResponseEntity<List<CommentDto>> getAllComments(@Positive Integer pageNumber, @Positive Integer pageSize);

    ResponseEntity<CommentDto> getCommentById(@Positive Long commentId);

    ResponseEntity<CommentDto> createComment(@Valid CommentToCreate commentToCreate, @Positive Long newsId);
}
