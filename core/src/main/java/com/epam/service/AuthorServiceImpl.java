package com.epam.service;

import com.epam.dao.AuthorRepository;
import com.epam.domain.Author;
import com.epam.exception.AuthorAlreadyExistsException;
import com.epam.exception.AuthorNotFoundException;
import com.epam.model.dto.AuthorDto;
import com.epam.model.dto.AuthorToCreate;
import com.epam.service.mapper.AuthorDtoMapper;
import com.epam.util.PageableUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorDtoMapper authorDtoMapper;

    @Override
    public List<AuthorDto> getAllAuthors(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageableUtil.getPageableWithoutSort(pageNumber - 1, pageSize, getCount());

        return authorDtoMapper.toAuthorDtoList(authorRepository.findAll(pageable));
    }

    @Override
    public AuthorDto getEntityById(Long authorId) {
        return authorDtoMapper.toAuthorDto(getAuthorById(authorId));
    }

    @Override
    @Transactional
    public void deleteEntityById(Long authorId) {
        authorRepository.delete(getAuthorById(authorId));
    }

    @Override
    @Transactional
    public AuthorDto createAuthor(AuthorToCreate authorToCreate) {
        String authorName = authorToCreate.getName().trim();
        checkForDuplicate(authorName);

        return authorDtoMapper.toAuthorDto(authorRepository.save(Author.builder().name(authorName).build()));
    }

    @Override
    public List<AuthorDto> getAuthorsByPartName(String partName, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageableUtil.getPageableWithoutSort(pageNumber - 1, pageSize, getCount());

        return authorDtoMapper.toAuthorDtoList(authorRepository.findAuthorsByNameContainsIgnoreCase(partName, pageable));
    }

    @Override
    public Author getAuthorById(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException("author.id.not.found", authorId));

    }

    private void checkForDuplicate(String name) {
        authorRepository.findAuthorByNameIgnoreCase(name).ifPresent(author -> {
            throw new AuthorAlreadyExistsException("author.exists", name);
        });
    }

    private long getCount() {
        return authorRepository.count();
    }
}
