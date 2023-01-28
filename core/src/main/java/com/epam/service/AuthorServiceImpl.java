package com.epam.service;

import com.epam.dao.AuthorRepository;
import com.epam.domain.Author;
import com.epam.exception.AuthorAlreadyExistsException;
import com.epam.exception.AuthorNotFoundException;
import com.epam.exception.PaginationException;
import com.epam.model.dto.AuthorDto;
import com.epam.model.dto.AuthorToCreate;
import com.epam.service.mapper.AuthorDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository;
    private final AuthorDtoMapper mapper;

    @Override
    public List<AuthorDto> getAllAuthors(Integer pageNumber, Integer pageSize) {
        Pageable pageable = getPageable(pageNumber - 1, pageSize);

        return mapper.toAuthorDtoList(repository.findAll(pageable));
    }

    @Override
    public AuthorDto getAuthorById(Long id) {
        return mapper.toAuthorDto(findAuthorById(id));
    }

    @Override
    @Transactional
    public void deleteAuthorById(Long id) {
        repository.delete(findAuthorById(id));
    }

    @Override
    @Transactional
    public AuthorDto createAuthor(AuthorToCreate authorToCreate) {
        String authorName = authorToCreate.getName().trim();
        checkForDuplicate(authorName);

        return mapper.toAuthorDto(repository.save(Author.builder().name(authorName).build()));
    }

    @Override
    public List<AuthorDto> getAuthorsByPartName(String partName, Integer pageNumber, Integer pageSize) {
        Pageable pageable = getPageable(pageNumber - 1, pageSize);

        return mapper.toAuthorDtoList(repository.findAuthorsByNameContainsIgnoreCase(partName, pageable));
    }

    private Pageable getPageable(Integer pageNumber, Integer pageSize) {
        long countFromDb = repository.count();
        long countFromRequest = pageNumber * pageSize;
        if (countFromDb <= countFromRequest) {
            throw new PaginationException("pagination.not.valid.data", pageNumber, pageSize);
        }

        return PageRequest.of(pageNumber, pageSize);
    }

    private Author findAuthorById(Long id) {
        return repository.findById(id).orElseThrow(() -> new AuthorNotFoundException("author.id.not.found", id));
    }

    private void checkForDuplicate(String name) {
        repository.findAuthorByNameIgnoreCase(name).ifPresent(author -> {
            throw new AuthorAlreadyExistsException("author.exists", name);
        });
    }
}
