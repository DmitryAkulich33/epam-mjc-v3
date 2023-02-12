package com.epam.controller;

import com.epam.domain.Comment_;
import com.epam.hateoas.assembler.impl.CommentCollectionAssembler;
import com.epam.model.dto.CommentDto;
import com.epam.model.dto.CommentToCreate;
import com.epam.model.dto.CommentToUpdate;
import com.epam.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final CommentService commentService;
    private final CommentCollectionAssembler commentCollectionAssembler;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<CommentDto>> getAllComments(@RequestParam(defaultValue = "${default.pageNumber}") @Positive int pageNumber,
                                                                      @RequestParam(defaultValue = "${default.pageSize}") @Positive int pageSize,
                                                                      @RequestParam(defaultValue = "DESC") @Pattern(regexp = "ASC|DESC") String sortType,
                                                                      @RequestParam(defaultValue = Comment_.CREATED) @Pattern(regexp = "created|modified") String sortField) {
        List<CommentDto> comments = commentService.getAllComments(pageNumber, pageSize, sortType, sortField);
        CollectionModel<CommentDto> model = commentCollectionAssembler.toCollectionModel(comments, pageNumber, pageSize, null, null);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDto> getEntityById(@PathVariable("commentId") @Positive Long commentId) {
        return new ResponseEntity<>(commentService.getEntityById(commentId), HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/news/{newsId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<CommentDto> createComment(@RequestBody @Valid CommentToCreate commentToCreate,
                                                    @PathVariable("newsId") @Positive Long newsId) {
        return new ResponseEntity<>(commentService.createComment(commentToCreate, newsId), HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{commentId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CommentDto> deleteEntityById(@PathVariable("commentId") @Positive Long commentId) {
        commentService.deleteEntityById(commentId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping(path = "/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<CommentDto> updateComment(@RequestBody @Valid CommentToUpdate commentToUpdate,
                                                    @PathVariable("commentId") @Positive Long commentId) {
        return new ResponseEntity<>(commentService.updateCommentById(commentToUpdate, commentId), HttpStatus.OK);
    }
}
