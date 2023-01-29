package com.epam.service;

import com.epam.dao.CommentRepository;
import com.epam.domain.Comment;
import com.epam.domain.News;
import com.epam.exception.CommentNotFoundException;
import com.epam.exception.PaginationException;
import com.epam.model.dto.CommentDto;
import com.epam.model.dto.CommentToCreate;
import com.epam.service.mapper.CommentDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentDtoMapper commentDtoMapper;
    private final NewsService newsService;

    @Override
    public List<CommentDto> getAllComments(Integer pageNumber, Integer pageSize) {
        Pageable pageable = getPageable(pageNumber - 1, pageSize);

        return commentDtoMapper.toCommentDtoList(commentRepository.findAll(pageable));
    }

    @Override
    public CommentDto getCommentDtoById(Long commentId) {
        return commentDtoMapper.toCommentDto(findCommentById(commentId));
    }

    @Override
    @Transactional
    public CommentDto createComment(CommentToCreate commentToCreate, Long newsId) {
        News news = newsService.getNewsById(newsId);
        Comment comment = Comment.builder()
                .content(commentToCreate.getContent())
                .news(news)
                .build();
        return commentDtoMapper.toCommentDto(commentRepository.save(comment));
    }

    @Override
    public void deleteCommentById(Long commentId) {
        commentRepository.delete(findCommentById(commentId));
    }

    private Pageable getPageable(Integer pageNumber, Integer pageSize) {
        long countFromDb = commentRepository.count();
        long countFromRequest = pageNumber * pageSize;
        if (countFromDb <= countFromRequest) {
            throw new PaginationException("pagination.not.valid.data", pageNumber, pageSize);
        }

        return PageRequest.of(pageNumber, pageSize);
    }

    private Comment findCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("comment.id.not.found", id));
    }

}
