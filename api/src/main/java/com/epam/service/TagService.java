package com.epam.service;

import com.epam.model.dto.TagDto;

import java.util.List;

public interface TagService {
    List<TagDto> getAllTags(Integer pageNumber, Integer pageSize);

    TagDto getTagById(Long id);
}
