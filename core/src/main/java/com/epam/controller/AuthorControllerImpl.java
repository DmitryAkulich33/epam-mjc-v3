package com.epam.controller;

import com.epam.hateoas.assembler.impl.AuthorCollectionAssembler;
import com.epam.model.dto.AuthorDto;
import com.epam.model.dto.AuthorToCreate;
import com.epam.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/authors")
public class AuthorControllerImpl implements AuthorController {
    private final AuthorService authorService;
    private final AuthorCollectionAssembler authorCollectionAssembler;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<AuthorDto>> getAllAuthors(@RequestParam(defaultValue = "${default.pageNumber}") @Positive int pageNumber,
                                                                    @RequestParam(defaultValue = "${default.pageSize}") @Positive int pageSize) {
        List<AuthorDto> authors = authorService.getAllAuthors(pageNumber, pageSize);
        CollectionModel<AuthorDto> model = authorCollectionAssembler.toCollectionModel(authors, pageNumber, pageSize, null, null);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{authorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorDto> getEntityById(@PathVariable("authorId") @Positive Long authorId) {
        return new ResponseEntity<>(authorService.getEntityById(authorId), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{authorId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AuthorDto> deleteEntityById(@PathVariable("authorId") @Positive Long authorId) {
        authorService.deleteEntityById(authorId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody @Valid AuthorToCreate authorToCreate) {
        return new ResponseEntity<>(authorService.createAuthor(authorToCreate), HttpStatus.CREATED);
    }

    @Override
    @GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<AuthorDto>> getAuthorsByPartName(@RequestParam @NotBlank String partName,
                                                                           @RequestParam(defaultValue = "${default.pageNumber}") @Positive int pageNumber,
                                                                           @RequestParam(defaultValue = "${default.pageSize}") @Positive int pageSize) {
        List<AuthorDto> authors = authorService.getAuthorsByPartName(partName.trim(), pageNumber, pageSize);
        CollectionModel<AuthorDto> model = authorCollectionAssembler.toCollectionModel(authors, pageNumber, pageSize, null, null);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
