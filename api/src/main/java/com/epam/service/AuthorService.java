package com.epam.service;

import com.epam.domain.Author;
import com.epam.model.dto.AuthorDto;
import com.epam.model.dto.AuthorToCreate;

import java.util.List;

public interface AuthorService extends BaseEntityService<AuthorDto> {
    List<AuthorDto> getAllAuthors(Integer pageNumber, Integer pageSize);

    AuthorDto createAuthor(AuthorToCreate authorToCreate);

    List<AuthorDto> getAuthorsByPartName(String partName, Integer pageNumber, Integer pageSize);

    Author getAuthorById(Long authorId);
}
