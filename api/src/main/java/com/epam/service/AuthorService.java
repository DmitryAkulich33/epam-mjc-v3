package com.epam.service;

import com.epam.domain.Author;
import com.epam.model.dto.AuthorDto;
import com.epam.model.dto.AuthorToCreate;

import java.util.List;

public interface AuthorService extends BaseEntityService<AuthorDto> {
    List<AuthorDto> getAllAuthors(int pageNumber, int pageSize);

    AuthorDto createAuthor(AuthorToCreate authorToCreate);

    List<AuthorDto> getAuthorsByPartName(String partName, int pageNumber, int pageSize);

    Author getAuthorById(Long authorId);
}
