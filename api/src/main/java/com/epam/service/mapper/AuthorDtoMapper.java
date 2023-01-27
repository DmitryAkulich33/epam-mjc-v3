package com.epam.service.mapper;

import com.epam.domain.Author;
import com.epam.model.dto.AuthorDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorDtoMapper {
    List<AuthorDto> toAuthorDtoList(List<Author> authors);

    AuthorDto toAuthorDto(Author author);
}
