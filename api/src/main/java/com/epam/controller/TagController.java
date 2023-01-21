package com.epam.controller;

import com.epam.model.dto.TagDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TagController {
    ResponseEntity<List<TagDto>> getAllTags(Integer pageNumber, Integer pageSize);
}
