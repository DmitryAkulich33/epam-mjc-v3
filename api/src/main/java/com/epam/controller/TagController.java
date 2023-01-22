package com.epam.controller;

import com.epam.model.dto.TagDto;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.Positive;
import java.util.List;

public interface TagController {
    ResponseEntity<List<TagDto>> getAllTags(@Positive Integer pageNumber, @Positive Integer pageSize);

    ResponseEntity<TagDto> getTagById(@Positive Long id);
}
