package com.epam.service;

import com.epam.dao.TagRepository;
import com.epam.domain.Tag;
import com.epam.exception.PaginationException;
import com.epam.exception.TagAlreadyExistsException;
import com.epam.exception.TagNotFoundException;
import com.epam.model.dto.TagDto;
import com.epam.model.dto.TagToCreate;
import com.epam.service.mapper.TagDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagDtoMapper tagDtoMapper;

    @Override
    public List<TagDto> getAllTags(Integer pageNumber, Integer pageSize) {
        Pageable pageable = getPageable(pageNumber - 1, pageSize);

        return tagDtoMapper.toTagDtoList(tagRepository.findAll(pageable));
    }

    @Override
    public TagDto getTagById(Long tagId) {
        return tagDtoMapper.toTagDto(findTagById(tagId));
    }

    @Override
    @Transactional
    public void deleteTagById(Long tagId) {
        tagRepository.delete(findTagById(tagId));
    }

    @Override
    @Transactional
    public TagDto createTag(TagToCreate tagToCreate) {
        String tagName = tagToCreate.getName().trim();
        checkForDuplicate(tagName);

        return tagDtoMapper.toTagDto(tagRepository.save(Tag.builder().name(tagName).build()));
    }

    @Override
    public List<TagDto> getTagsByPartName(String partName, Integer pageNumber, Integer pageSize) {
        Pageable pageable = getPageable(pageNumber - 1, pageSize);

        return tagDtoMapper.toTagDtoList(tagRepository.findTagsByNameContainsIgnoreCase(partName, pageable));
    }

    private Pageable getPageable(Integer pageNumber, Integer pageSize) {
        long countFromDb = tagRepository.count();
        long countFromRequest = pageNumber * pageSize;
        if (countFromDb <= countFromRequest) {
            throw new PaginationException("pagination.not.valid.data", pageNumber, pageSize);
        }

        return PageRequest.of(pageNumber, pageSize);
    }

    private Tag findTagById(Long id) {
        return tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException("tag.id.not.found", id));
    }

    private void checkForDuplicate(String name) {
        tagRepository.findTagByNameIgnoreCase(name).ifPresent(tag -> {
            throw new TagAlreadyExistsException("tag.exists", name);
        });
    }
}
