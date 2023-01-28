package com.epam.service.mapper;

import com.epam.domain.Comment;
import com.epam.model.dto.CommentDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentDtoMapper {
    List<CommentDto> toCommentDtoList(List<Comment> comments);

    CommentDto toCommentDto(Comment comment);
}
