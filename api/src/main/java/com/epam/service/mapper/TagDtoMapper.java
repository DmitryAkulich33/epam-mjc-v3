package com.epam.service.mapper;

import com.epam.domain.Tag;
import com.epam.model.dto.TagDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagDtoMapper {
    List<TagDto> toTagDtoList(List<Tag> tags);

    TagDto toTagDto(Tag tag);
}
