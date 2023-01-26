package com.epam.controller;

import com.epam.model.dto.TagDto;
import com.epam.model.dto.TagToCreate;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface TagController {
    ResponseEntity<List<TagDto>> getAllTags(@Positive Integer pageNumber, @Positive Integer pageSize);

    ResponseEntity<TagDto> getTagById(@Positive Long id);

    void deleteTagById(@Positive Long id);

    ResponseEntity<TagDto> createTag(@Valid TagToCreate tagToCreate);
}
