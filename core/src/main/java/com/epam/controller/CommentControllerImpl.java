package com.epam.controller;

import com.epam.model.dto.CommentDto;
import com.epam.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/comments")
public class CommentControllerImpl implements CommentController {
    private CommentService service;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentDto>> getAllComments(@RequestParam(defaultValue = "1") @Positive Integer pageNumber,
                                                           @RequestParam(defaultValue = "5") @Positive Integer pageSize) {
        List<CommentDto> comments = service.getAllComments(pageNumber, pageSize);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("id") @Positive Long id) {
        return new ResponseEntity<>(service.getCommentById(id), HttpStatus.OK);
    }
}
