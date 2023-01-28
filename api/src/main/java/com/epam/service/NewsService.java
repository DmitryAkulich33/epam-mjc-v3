package com.epam.service;

import com.epam.model.dto.NewsDto;

import java.util.List;

public interface NewsService {
    List<NewsDto> getAllNews(Integer pageNumber, Integer pageSize);

    NewsDto getNewsById(Long id);
}
