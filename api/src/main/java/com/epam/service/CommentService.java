package com.epam.service;

import com.epam.domain.Comment;
import com.epam.model.dto.CommentDto;
import com.epam.model.dto.CommentToCreate;
import com.epam.model.dto.CommentToUpdate;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAllComments(int pageNumber, int pageSize, String sortType, String sortField);

    CommentDto getCommentDtoById(Long commentId);

    CommentDto createComment(CommentToCreate commentToCreate, Long newsId);

    void deleteCommentById(Long commentId);

    CommentDto updateCommentById(CommentToUpdate commentToUpdate, Long commentId);

    Comment getCommentById(Long commentId);
}
