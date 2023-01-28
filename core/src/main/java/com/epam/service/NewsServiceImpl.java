package com.epam.service;

import com.epam.dao.NewsRepository;
import com.epam.exception.PaginationException;
import com.epam.model.dto.NewsDto;
import com.epam.service.mapper.NewsDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository repository;
    private final NewsDtoMapper mapper;

    @Override
    public List<NewsDto> getAllNews(Integer pageNumber, Integer pageSize) {
        Pageable pageable = getPageable(pageNumber - 1, pageSize);

        return mapper.toNewsDtoList(repository.findAll(pageable));
    }

    private Pageable getPageable(Integer pageNumber, Integer pageSize) {
        long countFromDb = repository.count();
        long countFromRequest = pageNumber * pageSize;
        if (countFromDb <= countFromRequest) {
            throw new PaginationException("pagination.not.valid.data", pageNumber, pageSize);
        }

        return PageRequest.of(pageNumber, pageSize);
    }
}
