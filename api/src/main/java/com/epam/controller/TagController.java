package com.epam.controller;

import com.epam.model.dto.TagDto;
import com.epam.model.dto.TagToCreate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public interface TagController extends BaseEntityController<TagDto> {
    ResponseEntity<CollectionModel<TagDto>> getAllTags(@Positive int pageNumber, @Positive int pageSize);

    ResponseEntity<TagDto> createTag(@Valid TagToCreate tagToCreate);

    ResponseEntity<CollectionModel<TagDto>> getTagsByPartName(@NotBlank String partName,
                                                              @Positive int pageNumber,
                                                              @Positive int pageSize);
}
