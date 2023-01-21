package com.epam.service;

import com.epam.dao.TagRepository;
import com.epam.domain.Tag;
import com.epam.model.dto.TagDto;
import com.epam.service.mapper.TagDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository repository;
    private final TagDtoMapper mapper;

    @Override
    public List<TagDto> getAllTags() {
        return mapper.toTagDtoList((List<Tag>) repository.findAll());
    }
}
