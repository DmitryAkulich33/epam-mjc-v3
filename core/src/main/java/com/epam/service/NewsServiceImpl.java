package com.epam.service;

import com.epam.dao.CommentRepository;
import com.epam.dao.NewsRepository;
import com.epam.domain.News;
import com.epam.exception.NewsNotFoundException;
import com.epam.exception.PaginationException;
import com.epam.model.dto.AuthorDto;
import com.epam.model.dto.CommentDto;
import com.epam.model.dto.NewsDto;
import com.epam.model.dto.TagDto;
import com.epam.service.mapper.AuthorDtoMapper;
import com.epam.service.mapper.CommentDtoMapper;
import com.epam.service.mapper.NewsDtoMapper;
import com.epam.service.mapper.TagDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final NewsDtoMapper newsDtoMapper;
    private final TagDtoMapper tagDtoMapper;
    private final AuthorDtoMapper authorDtoMapper;
    private final CommentDtoMapper commentDtoMapper;

    @Override
    public List<NewsDto> getAllNews(Integer pageNumber, Integer pageSize) {
        Pageable pageable = getPageable(pageNumber - 1, pageSize);

        return newsDtoMapper.toNewsDtoList(newsRepository.findAll(pageable));
    }

    @Override
    public NewsDto getNewsById(Long newsId) {
        return newsDtoMapper.toNewsDto(findNewsById(newsId));
    }

    @Override
    public List<TagDto> getNewsTags(Long newsId) {
        News news = findNewsById(newsId);

        return tagDtoMapper.toTagDtoList(news.getTags());
    }

    @Override
    public AuthorDto getNewsAuthor(Long newsId) {
        News news = findNewsById(newsId);

        return authorDtoMapper.toAuthorDto(news.getAuthor());
    }

    @Override
    public List<CommentDto> getNewsComments(Long newsId) {
        News news = findNewsById(newsId);

        return commentDtoMapper.toCommentDtoList(news.getComments());
    }

    private Pageable getPageable(Integer pageNumber, Integer pageSize) {
        long countFromDb = newsRepository.count();
        long countFromRequest = pageNumber * pageSize;
        if (countFromDb <= countFromRequest) {
            throw new PaginationException("pagination.not.valid.data", pageNumber, pageSize);
        }

        return PageRequest.of(pageNumber, pageSize);
    }

    private News findNewsById(Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new NewsNotFoundException("news.id.not.found", id));
    }
}
