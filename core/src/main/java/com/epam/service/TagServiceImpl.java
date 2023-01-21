package com.epam.service;

import com.epam.dao.TagRepository;
import com.epam.model.dto.TagDto;
import com.epam.service.mapper.TagDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    private Pageable getPageable(Integer pageNumber, Integer pageSize) {
        long countFromDb = repository.count();
        long countFromRequest = pageNumber * pageSize;
        if (countFromDb <= countFromRequest) {
            // TODO: add exception
            throw new RuntimeException();
        }

        return PageRequest.of(pageNumber, pageSize);
    }
}
