package com.epam.controller;

import com.epam.model.dto.CommentDto;
import com.epam.model.dto.CommentToCreate;
import com.epam.model.dto.CommentToUpdate;
import com.epam.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/comments")
public class CommentControllerImpl implements CommentController {
    private CommentService commentService;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentDto>> getAllComments(@RequestParam(defaultValue = "1") @Positive Integer pageNumber,
                                                           @RequestParam(defaultValue = "5") @Positive Integer pageSize,
                                                           @RequestParam(defaultValue = "DESC") @Pattern(regexp = "ASC|DESC") String sortType,
                                                           @RequestParam(defaultValue = "created") @Pattern(regexp = "created|modified") String sortField) {
        List<CommentDto> comments = commentService.getAllComments(pageNumber, pageSize, sortType, sortField);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("commentId") @Positive Long commentId) {
        return new ResponseEntity<>(commentService.getCommentDtoById(commentId), HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/news/{newsId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDto> createComment(@RequestBody @Valid CommentToCreate commentToCreate,
                                                    @PathVariable("newsId") @Positive Long newsId) {
        return new ResponseEntity<>(commentService.createComment(commentToCreate, newsId), HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentById(@PathVariable("commentId") @Positive Long commentId) {
        commentService.deleteCommentById(commentId);
    }

    @Override
    @PatchMapping(path = "/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDto> updateComment(@RequestBody @Valid CommentToUpdate commentToUpdate,
                                                    @PathVariable("commentId") @Positive Long commentId) {
        return new ResponseEntity<>(commentService.updateCommentById(commentToUpdate, commentId), HttpStatus.OK);
    }
}
