package com.epam.service;

import com.epam.model.dto.CommentDto;
import com.epam.model.dto.CommentToCreate;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAllComments(Integer pageNumber, Integer pageSize);

    CommentDto getCommentDtoById(Long commentId);

    CommentDto createComment(CommentToCreate commentToCreate, Long newsId);

    void deleteCommentById(Long commentId);
}
