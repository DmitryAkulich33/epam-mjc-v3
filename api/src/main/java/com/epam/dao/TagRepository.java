package com.epam.dao;

import com.epam.domain.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
    List<Tag> findAll(Pageable pageable);

    Optional<Tag> findTagByName(String name);
}
