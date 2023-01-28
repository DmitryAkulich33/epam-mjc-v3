package com.epam.controller;

import com.epam.model.dto.AuthorDto;
import com.epam.model.dto.AuthorToCreate;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

public interface AuthorController {
    ResponseEntity<List<AuthorDto>> getAllAuthors(@Positive Integer pageNumber, @Positive Integer pageSize);

    ResponseEntity<AuthorDto> getAuthorById(@Positive Long id);

    void deleteAuthorById(@Positive Long id);

    ResponseEntity<AuthorDto> createAuthor(@Valid AuthorToCreate authorToCreate);

    ResponseEntity<List<AuthorDto>> getAuthorsByPartName(@NotBlank String partName,
                                                         @Positive Integer pageNumber,
                                                         @Positive Integer pageSize);
}