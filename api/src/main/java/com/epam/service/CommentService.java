package com.epam.service;

import com.epam.model.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAllComments(Integer pageNumber, Integer pageSize);

    CommentDto getCommentById(Long commentId);
}
