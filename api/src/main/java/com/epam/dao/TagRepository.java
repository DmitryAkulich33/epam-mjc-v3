package com.epam.dao;

import com.epam.domain.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends AbstractRepository<Tag> {
    Optional<Tag> findTagByNameIgnoreCase(String name);

    List<Tag> findTagsByNameContainsIgnoreCase(String name, Pageable pageable);
}
