package com.epam.service;

import com.epam.dao.CommentRepository;
import com.epam.exception.PaginationException;
import com.epam.model.dto.CommentDto;
import com.epam.service.mapper.CommentDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final CommentDtoMapper mapper;

    @Override
    public List<CommentDto> getAllComments(Integer pageNumber, Integer pageSize) {
        Pageable pageable = getPageable(pageNumber - 1, pageSize);

        return mapper.toCommentDtoList(repository.findAll(pageable));
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
