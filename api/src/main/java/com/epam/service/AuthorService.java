package com.epam.service;

import com.epam.domain.Author;
import com.epam.model.dto.AuthorDto;
import com.epam.model.dto.AuthorToCreate;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAllAuthors(int pageNumber,int pageSize);

    AuthorDto getAuthorDtoById(Long authorId);

    void deleteAuthorById(Long authorId);

    AuthorDto createAuthor(AuthorToCreate authorToCreate);

    List<AuthorDto> getAuthorsByPartName(String partName, int pageNumber, int pageSize);

    Author getAuthorById(Long authorId);
}
