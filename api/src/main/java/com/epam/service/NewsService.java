package com.epam.service;

import com.epam.domain.News;
import com.epam.model.dto.*;

import java.util.List;

public interface NewsService extends BaseEntityService<NewsDto> {
    List<NewsDto> getAllNews(int pageNumber, int pageSize, String sortType, String sortField);

    List<TagDto> getNewsTags(Long newsId);

    AuthorDto getNewsAuthor(Long newsId);

    List<CommentDto> getNewsComments(Long newsId);

    News getNewsById(Long newsId);

    NewsDto createNews(NewsToCreate newsToCreate, Long authorId);


    NewsDto updateNewsById(NewsToUpdate newsToUpdate, Long newsId);
}
