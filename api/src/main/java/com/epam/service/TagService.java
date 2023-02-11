package com.epam.service;

import com.epam.domain.Tag;
import com.epam.model.dto.TagDto;
import com.epam.model.dto.TagToCreate;

import java.util.List;

public interface TagService extends BaseEntityService<TagDto> {
    List<TagDto> getAllTags(int pageNumber, int pageSize);

    TagDto createTag(TagToCreate tagToCreate);

    List<TagDto> getTagsByPartName(String partName, int pageNumber, int pageSize);

    List<Tag> updateTags(List<TagToCreate> tagsToCreate);
}
