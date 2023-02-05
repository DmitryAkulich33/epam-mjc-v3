package com.epam.controller;

import com.epam.model.dto.AuthorDto;
import com.epam.model.dto.AuthorToCreate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public interface AuthorController extends BaseEntityController<AuthorDto> {
    ResponseEntity<CollectionModel<AuthorDto>> getAllAuthors(@Positive Integer pageNumber, @Positive Integer pageSize);

    ResponseEntity<AuthorDto> createAuthor(@Valid AuthorToCreate authorToCreate);

    ResponseEntity<CollectionModel<AuthorDto>> getAuthorsByPartName(@NotBlank String partName,
                                                                    @Positive Integer pageNumber,
                                                                    @Positive Integer pageSize);
}
