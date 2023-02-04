package com.epam.controller;

import com.epam.model.dto.TagDto;
import com.epam.model.dto.TagToCreate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

public interface TagController {
    ResponseEntity<CollectionModel<TagDto>> getAllTags(@Positive int pageNumber, @Positive int pageSize);

    ResponseEntity<TagDto> getTagById(@Positive Long tagId);

    ResponseEntity<TagDto> deleteTagById(@Positive Long tagId);

    ResponseEntity<TagDto> createTag(@Valid TagToCreate tagToCreate);

    ResponseEntity<List<TagDto>> getTagsByPartName(@NotBlank String partName,
                                                   @Positive int pageNumber,
                                                   @Positive int pageSize);
}
