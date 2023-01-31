package com.epam.service;

import com.epam.dao.NewsRepository;
import com.epam.domain.Author;
import com.epam.domain.News;
import com.epam.domain.Tag;
import com.epam.exception.NewsAlreadyExistsException;
import com.epam.exception.NewsNotFoundException;
import com.epam.exception.PaginationException;
import com.epam.model.dto.*;
import com.epam.service.mapper.AuthorDtoMapper;
import com.epam.service.mapper.CommentDtoMapper;
import com.epam.service.mapper.NewsDtoMapper;
import com.epam.service.mapper.TagDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final NewsDtoMapper newsDtoMapper;
    private final TagDtoMapper tagDtoMapper;
    private final AuthorDtoMapper authorDtoMapper;
    private final CommentDtoMapper commentDtoMapper;
    private final AuthorService authorService;
    private final TagService tagService;

    @Override
    public List<NewsDto> getAllNews(Integer pageNumber, Integer pageSize) {
        Pageable pageable = getPageable(pageNumber - 1, pageSize);

        return newsDtoMapper.toNewsDtoList(newsRepository.findAll(pageable));
    }

    @Override
    public NewsDto getNewsDtoById(Long newsId) {
        return newsDtoMapper.toNewsDto(getNewsById(newsId));
    }

    @Override
    public List<TagDto> getNewsTags(Long newsId) {
        News news = getNewsById(newsId);

        return tagDtoMapper.toTagDtoList(news.getTags());
    }

    @Override
    public AuthorDto getNewsAuthor(Long newsId) {
        News news = getNewsById(newsId);

        return authorDtoMapper.toAuthorDto(news.getAuthor());
    }

    @Override
    public List<CommentDto> getNewsComments(Long newsId) {
        News news = getNewsById(newsId);

        return commentDtoMapper.toCommentDtoList(news.getComments());
    }

    @Override
    public News getNewsById(Long newsId) {
        return newsRepository.findById(newsId).orElseThrow(() -> new NewsNotFoundException("news.id.not.found", newsId));
    }

    @Override
    @Transactional
    public NewsDto createNews(NewsToCreate newsToCreate, Long authorId) {
        String newTitle = newsToCreate.getTitle().trim();
        checkForDuplicate(newTitle);
        Author author = authorService.getAuthorById(authorId);
        List<Tag> tags = tagService.updateTags(newsToCreate.getTags());
        News news = News.builder()
                .author(author)
                .content(newsToCreate.getContent())
                .title(newTitle)
                .tags(tags)
                .build();

        return newsDtoMapper.toNewsDto(newsRepository.save(news));
    }

    @Override
    public void deleteNewsById(Long newsId) {
        newsRepository.delete(getNewsById(newsId));
    }

    private Pageable getPageable(Integer pageNumber, Integer pageSize) {
        long countFromDb = newsRepository.count();
        long countFromRequest = pageNumber * pageSize;
        if (countFromDb <= countFromRequest && countFromDb != 0) {
            throw new PaginationException("pagination.not.valid.data", pageNumber, pageSize);
        }

        return PageRequest.of(pageNumber, pageSize);
    }

    private void checkForDuplicate(String title) {
        newsRepository.findNewsByTitleIgnoreCase(title).ifPresent(news -> {
            throw new NewsAlreadyExistsException("news.exists", title);
        });
    }
}
