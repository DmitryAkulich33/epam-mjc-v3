package com.epam.service;

import com.epam.dao.CommentRepository;
import com.epam.domain.Comment;
import com.epam.domain.News;
import com.epam.exception.CommentNotFoundException;
import com.epam.model.dto.CommentDto;
import com.epam.model.dto.CommentToCreate;
import com.epam.model.dto.CommentToUpdate;
import com.epam.service.mapper.CommentDtoMapper;
import com.epam.util.PageableUtil;
import lombok.AllArgsConstructor;
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
    public List<CommentDto> getAllComments(Integer pageNumber, Integer pageSize, String sortType, String sortField) {
        Pageable pageable = PageableUtil.getPageableWithSort(pageNumber - 1, pageSize, sortType, sortField, commentRepository);

        return commentDtoMapper.toCommentDtoList(commentRepository.findAll(pageable));
    }

    @Override
    public CommentDto getCommentDtoById(Long commentId) {
        return commentDtoMapper.toCommentDto(getCommentById(commentId));
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
        commentRepository.delete(getCommentById(commentId));
    }

    @Override
    @Transactional
    public CommentDto updateCommentById(CommentToUpdate commentToUpdate, Long commentId) {
        Comment comment = getCommentById(commentId);
        comment.setContent(commentToUpdate.getContent().trim());

        return commentDtoMapper.toCommentDto(commentRepository.save(comment));
    }

    @Override
    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("comment.id.not.found", commentId));

    }
}
