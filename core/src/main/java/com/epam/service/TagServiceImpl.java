package com.epam.service;

import com.epam.dao.TagRepository;
import com.epam.domain.Tag;
import com.epam.exception.TagAlreadyExistsException;
import com.epam.exception.TagNotFoundException;
import com.epam.model.dto.TagDto;
import com.epam.model.dto.TagToCreate;
import com.epam.service.mapper.TagDtoMapper;
import com.epam.util.PageableUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagDtoMapper tagDtoMapper;

    @Override
    public List<TagDto> getAllTags(int pageNumber, int pageSize) {
        Pageable pageable = PageableUtil.getPageableWithoutSort(pageNumber - 1, pageSize, getCount());

        return tagDtoMapper.toTagDtoList(tagRepository.findAll(pageable));
    }

    @Override
    public TagDto getTagDtoById(Long tagId) {
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
    public List<TagDto> getTagsByPartName(String partName, int pageNumber, int pageSize) {
        Pageable pageable = PageableUtil.getPageableWithoutSort(pageNumber - 1, pageSize, getCount());

        return tagDtoMapper.toTagDtoList(tagRepository.findTagsByNameContainsIgnoreCase(partName, pageable));
    }

    @Override
    public List<Tag> updateTags(List<TagToCreate> tagsToCreate) {
        Set<TagToCreate> uniqueTags = new HashSet<>(tagsToCreate);

        return uniqueTags.stream()
                .map(tag -> tagRepository.findTagByNameIgnoreCase(tag.getName().trim())
                        .orElseGet(() -> tagRepository.save(Tag.builder().name(tag.getName().trim()).build())))
                .collect(Collectors.toList());
    }

    private Tag findTagById(Long id) {
        return tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException("tag.id.not.found", id));
    }

    private void checkForDuplicate(String name) {
        tagRepository.findTagByNameIgnoreCase(name).ifPresent(tag -> {
            throw new TagAlreadyExistsException("tag.exists", name);
        });
    }

    private long getCount() {
        return tagRepository.count();
    }
}
