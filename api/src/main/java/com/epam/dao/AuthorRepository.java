package com.epam.dao;

import com.epam.domain.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends AbstractRepository<Author> {
    Optional<Author> findAuthorByNameIgnoreCase(String name);

    List<Author> findAuthorsByNameContainsIgnoreCase(String name, Pageable pageable);
}
