package com.epam.controller;

import com.epam.model.dto.AuthorDto;
import com.epam.model.dto.CommentDto;
import com.epam.model.dto.NewsDto;
import com.epam.model.dto.TagDto;
import com.epam.service.NewsService;
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
@RequestMapping(value = "/api/v1/news")
public class NewsControllerImpl implements NewsController {
    private final NewsService service;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NewsDto>> getAllNews(@RequestParam(defaultValue = "1") @Positive Integer pageNumber,
                                                    @RequestParam(defaultValue = "5") @Positive Integer pageSize) {
        List<NewsDto> news = service.getAllNews(pageNumber, pageSize);

        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{newsId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NewsDto> getNewsById(@PathVariable("newsId") @Positive Long newsId) {
        return new ResponseEntity<>(service.getNewsById(newsId), HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{newsId}/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TagDto>> getNewsTags(@PathVariable("newsId") @Positive Long newsId) {
        List<TagDto> tags = service.getNewsTags(newsId);

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{newsId}/authors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorDto> getNewsAuthor(@PathVariable("newsId") @Positive Long newsId) {
        AuthorDto author = service.getNewsAuthor(newsId);

        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{newsId}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentDto>> getNewsComments(@PathVariable("newsId") @Positive Long newsId) {
        List<CommentDto> comments = service.getNewsComments(newsId);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
