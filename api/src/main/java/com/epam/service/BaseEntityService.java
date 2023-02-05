package com.epam.service;

public interface BaseEntityService<T> {
    T getEntityById(Long authorId);

    void deleteEntityById(Long authorId);
}
