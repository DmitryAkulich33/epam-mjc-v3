package com.epam.service;

import com.epam.dao.CommentRepository;
import com.epam.domain.Comment;
import com.epam.exception.CommentNotFoundException;
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

    @Override
    public CommentDto getCommentById(Long id) {
        return mapper.toCommentDto(findCommentById(id));
    }

    private Pageable getPageable(Integer pageNumber, Integer pageSize) {
        long countFromDb = repository.count();
        long countFromRequest = pageNumber * pageSize;
        if (countFromDb <= countFromRequest) {
            throw new PaginationException("pagination.not.valid.data", pageNumber, pageSize);
        }

        return PageRequest.of(pageNumber, pageSize);
    }

    private Comment findCommentById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CommentNotFoundException("comment.id.not.found", id));
    }

}
