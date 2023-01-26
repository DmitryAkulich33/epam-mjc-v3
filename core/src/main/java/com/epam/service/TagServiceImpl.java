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
    private final TagRepository repository;
    private final TagDtoMapper mapper;

    @Override
    public List<TagDto> getAllTags(Integer pageNumber, Integer pageSize) {
        Pageable pageable = getPageable(pageNumber - 1, pageSize);

        return mapper.toTagDtoList(repository.findAll(pageable));
    }

    @Override
    public TagDto getTagById(Long id) {
        return mapper.toTagDto(findTagById(id));
    }

    @Override
    @Transactional
    public void deleteTagById(Long id) {
        repository.delete(findTagById(id));
    }

    @Override
    @Transactional
    public TagDto createTag(TagToCreate tagToCreate) {
        String tagName = tagToCreate.getName();
        checkForDuplicate(tagName);

        return mapper.toTagDto(repository.save(Tag.builder().name(tagName).build()));
    }

    private Pageable getPageable(Integer pageNumber, Integer pageSize) {
        long countFromDb = repository.count();
        long countFromRequest = pageNumber * pageSize;
        if (countFromDb <= countFromRequest) {
            throw new PaginationException("pagination.not.valid.data", pageNumber, pageSize);
        }

        return PageRequest.of(pageNumber, pageSize);
    }

    private Tag findTagById(Long id) {
        return repository.findById(id).orElseThrow(() -> new TagNotFoundException("tag.id.not.found", id));
    }

    private void checkForDuplicate(String name) {
        repository.findTagByName(name).ifPresent(tag -> {
            throw new TagAlreadyExistsException("tag.exists", name);
        });
    }
}
