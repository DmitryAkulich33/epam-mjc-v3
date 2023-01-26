package com.epam.service;

import com.epam.model.dto.TagDto;
import com.epam.model.dto.TagToCreate;

import java.util.List;

public interface TagService {
    List<TagDto> getAllTags(Integer pageNumber, Integer pageSize);

    TagDto getTagById(Long id);

    void deleteTagById(Long id);

    TagDto createTag(TagToCreate tagToCreate);

    List<TagDto> getTagsByPartName(String partName, Integer pageNumber, Integer pageSize);
}
