package com.epam.service;

import com.epam.domain.News;
import com.epam.model.dto.*;

import java.util.List;

public interface NewsService {
    List<NewsDto> getAllNews(Integer pageNumber, Integer pageSize);

    NewsDto getNewsDtoById(Long newsId);

    List<TagDto> getNewsTags(Long newsId);

    AuthorDto getNewsAuthor(Long newsId);

    List<CommentDto> getNewsComments(Long newsId);

    News getNewsById(Long newsId);

    NewsDto createNews(NewsToCreate newsToCreate, Long authorId);

    void deleteNewsById(Long newsId);
}
