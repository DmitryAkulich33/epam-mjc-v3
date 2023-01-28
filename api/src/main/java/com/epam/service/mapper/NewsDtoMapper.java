package com.epam.service.mapper;

import com.epam.domain.News;
import com.epam.model.dto.AuthorDto;
import com.epam.model.dto.CommentDto;
import com.epam.model.dto.NewsDto;
import com.epam.model.dto.TagDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AuthorDto.class, TagDto.class, CommentDto.class})
public interface NewsDtoMapper {
    List<NewsDto> toNewsDtoList(List<News> news);

    NewsDto toNewsDto(News news);
}
