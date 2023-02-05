package com.epam.controller;

import org.springframework.http.ResponseEntity;

import javax.validation.constraints.Positive;

public interface BaseEntityController<T> {
    ResponseEntity<T> getEntityById(@Positive Long authorId);

    ResponseEntity<T> deleteEntityById(@Positive Long authorId);
}
