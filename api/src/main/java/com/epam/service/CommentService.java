package com.epam.service;

import com.epam.domain.Comment;
import com.epam.model.dto.CommentDto;
import com.epam.model.dto.CommentToCreate;
import com.epam.model.dto.CommentToUpdate;

import java.util.List;

public interface CommentService extends BaseEntityService<CommentDto> {
    List<CommentDto> getAllComments(Integer pageNumber, Integer pageSize, String sortType, String sortField);

    CommentDto createComment(CommentToCreate commentToCreate, Long newsId);

    CommentDto updateCommentById(CommentToUpdate commentToUpdate, Long commentId);

    Comment getCommentById(Long commentId);
}
