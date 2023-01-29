package com.epam.controller;

import com.epam.model.dto.TagDto;
import com.epam.model.dto.TagToCreate;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

public interface TagController {
    ResponseEntity<List<TagDto>> getAllTags(@Positive Integer pageNumber, @Positive Integer pageSize);

    ResponseEntity<TagDto> getTagById(@Positive Long tagId);

    void deleteTagById(@Positive Long tagId);

    ResponseEntity<TagDto> createTag(@Valid TagToCreate tagToCreate);

    ResponseEntity<List<TagDto>> getTagsByPartName(@NotBlank String partName,
                                                   @Positive Integer pageNumber,
                                                   @Positive Integer pageSize);
}
