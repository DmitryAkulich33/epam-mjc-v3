package com.epam.service;

import com.epam.model.dto.AuthorDto;
import com.epam.model.dto.AuthorToCreate;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAllAuthors(Integer pageNumber, Integer pageSize);

    AuthorDto getAuthorById(Long authorId);

    void deleteAuthorById(Long authorId);

    AuthorDto createAuthor(AuthorToCreate authorToCreate);

    List<AuthorDto> getAuthorsByPartName(String partName, Integer pageNumber, Integer pageSize);
}
