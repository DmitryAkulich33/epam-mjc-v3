package com.epam.controller;

import com.epam.model.dto.AuthorDto;
import com.epam.model.dto.AuthorToCreate;
import com.epam.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private final AuthorService service;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthorDto>> getAllAuthors(@RequestParam(defaultValue = "1") @Positive Integer pageNumber,
                                                         @RequestParam(defaultValue = "5") @Positive Integer pageSize) {
        List<AuthorDto> authors = service.getAllAuthors(pageNumber, pageSize);

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable("id") @Positive Long id) {
        return new ResponseEntity<>(service.getAuthorById(id), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthorById(@PathVariable("id") @Positive Long id) {
        service.deleteAuthorById(id);
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody @Valid AuthorToCreate authorToCreate) {
        return new ResponseEntity<>(service.createAuthor(authorToCreate), HttpStatus.CREATED);
    }

    @Override
    @GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthorDto>> getAuthorsByPartName(@RequestParam @NotBlank String partName,
                                                                @RequestParam(defaultValue = "1") @Positive Integer pageNumber,
                                                                @RequestParam(defaultValue = "5") @Positive Integer pageSize) {
        List<AuthorDto> authors = service.getAuthorsByPartName(partName.trim(), pageNumber, pageSize);

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
}
