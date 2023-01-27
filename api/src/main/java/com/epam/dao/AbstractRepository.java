package com.epam.dao;

import com.epam.domain.BaseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AbstractRepository<T extends BaseEntity> extends CrudRepository<T, Long> {
    List<T> findAll(Pageable pageable);
}
