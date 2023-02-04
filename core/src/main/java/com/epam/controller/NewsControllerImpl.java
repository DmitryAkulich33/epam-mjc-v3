package com.epam.controller;

import com.epam.hateoas.assembler.impl.CommentCollectionAssembler;
import com.epam.hateoas.assembler.impl.NewsCollectionAssembler;
import com.epam.hateoas.assembler.impl.TagCollectionAssembler;
import com.epam.model.dto.*;
import com.epam.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
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
@RequestMapping(value = "/api/v1/news")
public class NewsControllerImpl implements NewsController {
    private final NewsService newsService;
    private final NewsCollectionAssembler newsCollectionAssembler;
    private final CommentCollectionAssembler commentCollectionAssembler;
    private final TagCollectionAssembler tagCollectionAssembler;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<NewsDto>> getAllNews(@RequestParam(defaultValue = "1") @Positive Integer pageNumber,
                                                               @RequestParam(defaultValue = "5") @Positive Integer pageSize,
                                                               @RequestParam(defaultValue = "DESC") @Pattern(regexp = "ASC|DESC") String sortType,
                                                               @RequestParam(defaultValue = "created") @Pattern(regexp = "created|modified") String sortField) {
        List<NewsDto> news = newsService.getAllNews(pageNumber, pageSize, sortType, sortField);
        CollectionModel<NewsDto> model = newsCollectionAssembler.toCollectionModel(news, pageNumber, pageSize, sortType, sortField);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{newsId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NewsDto> getNewsById(@PathVariable("newsId") @Positive Long newsId) {
        return new ResponseEntity<>(newsService.getNewsDtoById(newsId), HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{newsId}/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<TagDto>> getNewsTags(@PathVariable("newsId") @Positive Long newsId) {
        List<TagDto> tags = newsService.getNewsTags(newsId);
        CollectionModel<TagDto> model = tagCollectionAssembler.toCollectionModel(tags, 1, 10, null, null);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{newsId}/authors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorDto> getNewsAuthor(@PathVariable("newsId") @Positive Long newsId) {
        AuthorDto author = newsService.getNewsAuthor(newsId);

        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{newsId}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<CommentDto>> getNewsComments(@PathVariable("newsId") @Positive Long newsId) {
        List<CommentDto> comments = newsService.getNewsComments(newsId);
        CollectionModel<CommentDto> model = commentCollectionAssembler.toCollectionModel(comments, 1, 10, null, null);
        
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/authors/{authorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NewsDto> createNews(@RequestBody @Valid NewsToCreate newsToCreate,
                                              @PathVariable("authorId") @Positive Long authorId) {
        return new ResponseEntity<>(newsService.createNews(newsToCreate, authorId), HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{newsId}")
    public ResponseEntity<NewsDto> deleteNewsById(@PathVariable("newsId") @Positive Long newsId) {
        newsService.deleteNewsById(newsId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping(path = "/{newsId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NewsDto> updateNews(@RequestBody @Valid NewsToUpdate newsToUpdate,
                                              @PathVariable("newsId") @Positive Long newsId) {
        return new ResponseEntity<>(newsService.updateNewsById(newsToUpdate, newsId), HttpStatus.OK);
    }
}
