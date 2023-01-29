package com.epam.service;

import com.epam.domain.News;
import com.epam.model.dto.AuthorDto;
import com.epam.model.dto.CommentDto;
import com.epam.model.dto.NewsDto;
import com.epam.model.dto.TagDto;

import java.util.List;

public interface NewsService {
    List<NewsDto> getAllNews(Integer pageNumber, Integer pageSize);

    NewsDto getNewsDtoById(Long newsId);

    List<TagDto> getNewsTags(Long newsId);

    AuthorDto getNewsAuthor(Long newsId);

    List<CommentDto> getNewsComments(Long newsId);

    News getNewsById(Long newsId);
}
