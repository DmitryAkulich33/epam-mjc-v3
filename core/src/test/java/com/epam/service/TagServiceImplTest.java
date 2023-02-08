package com.epam.service;

import com.epam.dao.TagRepository;
import com.epam.domain.Tag;
import com.epam.exception.PaginationException;
import com.epam.exception.TagAlreadyExistsException;
import com.epam.exception.TagNotFoundException;
import com.epam.model.dto.TagDto;
import com.epam.model.dto.TagToCreate;
import com.epam.service.mapper.TagDtoMapper;
import com.epam.util.PageableUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {
    @Mock
    private TagRepository mockTagRepository;
    @Mock
    private TagDtoMapper mockTagDtoMapper;

    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    public void testGetTagById() {
        Tag tag = new Tag();
        tag.setId(1L);
        tag.setName("test");

        TagDto tagDto1 = new TagDto();
        tagDto1.setId(1L);
        tagDto1.setName("test");

        when(mockTagRepository.findById(anyLong())).thenReturn(Optional.of(tag));
        when(mockTagDtoMapper.toTagDto(tag)).thenReturn(tagDto1);
        TagDto actual = tagService.getEntityById(1L);

        assertEquals(tagDto1, actual);
    }

    @Test
    public void testGetTagById_TagNotFoundException() {
        when(mockTagRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(TagNotFoundException.class, () -> {
            tagService.getEntityById(1L);
        });
    }

    @Test
    public void testCreateTag() {
        TagToCreate tagToCreate = new TagToCreate();
        tagToCreate.setName("test");
        Tag tag = new Tag();
        TagDto expected = new TagDto();
        expected.setName("test");

        when(mockTagRepository.save(Tag.builder().name("test").build())).thenReturn(tag);
        when(mockTagDtoMapper.toTagDto(tag)).thenReturn(expected);

        TagDto actual = tagService.createTag(tagToCreate);

        assertEquals(expected, actual);
    }

    @Test
    public void testCreateTag_TagAlreadyExistsException() {
        TagToCreate tagToCreate = new TagToCreate();
        tagToCreate.setName("test");

        when(mockTagRepository.findTagByNameIgnoreCase(anyString())).thenThrow(new TagAlreadyExistsException("Error message"));

        assertThrows(TagAlreadyExistsException.class, () -> {
            tagService.createTag(tagToCreate);
        });
    }

    @Test
    public void testDeleteTag() {
        Tag tag = new Tag();

        when(mockTagRepository.findById(anyLong())).thenReturn(Optional.of(tag));
        tagService.deleteEntityById(anyLong());

        verify(mockTagRepository).findById(anyLong());
        verify(mockTagRepository).delete(tag);
    }

    @Test
    public void testDeleteTag_TagNotFoundException() {
        when(mockTagRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(TagNotFoundException.class, () -> {
            tagService.deleteEntityById(anyLong());
        });
    }

    @Test
    public void testGetTags() {
        List<Tag> tags = new ArrayList<>();
        long count = 5L;
        Pageable pageable = PageableUtil.getPageableWithoutSort(0, 10, count);
        List<TagDto> tagDtoList = new ArrayList<>();

        when(mockTagRepository.findAll(pageable)).thenReturn(tags);
        when(mockTagDtoMapper.toTagDtoList(tags)).thenReturn(tagDtoList);
        when(mockTagRepository.count()).thenReturn(count);

        List<TagDto> actual = tagService.getAllTags(1, 10);

        assertEquals(tagDtoList, actual);
    }

    @ParameterizedTest
    @MethodSource("inValidPaginationParams")
    public void testGetTags_invalidPagination(int pageNumber, int pageSize, long count) {
        when(mockTagRepository.count()).thenReturn(count);

        assertThrows(PaginationException.class, () -> {
            tagService.getAllTags(pageNumber, pageSize);
        });
    }

    @Test
    public void testGetTagsByPartName() {
        List<Tag> tags = new ArrayList<>();
        long count = 5L;
        String partName = "test";
        Pageable pageable = PageableUtil.getPageableWithoutSort(0, 10, count);
        List<TagDto> tagDtoList = new ArrayList<>();

        when(mockTagRepository.findTagsByNameContainsIgnoreCase(partName, pageable)).thenReturn(tags);
        when(mockTagDtoMapper.toTagDtoList(tags)).thenReturn(tagDtoList);
        when(mockTagRepository.count()).thenReturn(count);

        List<TagDto> actual = tagService.getTagsByPartName(partName, 1, 10);

        assertEquals(tagDtoList, actual);
    }

    @ParameterizedTest
    @MethodSource("inValidPaginationParams")
    public void testGetTagsByPartName_invalidPagination(int pageNumber, int pageSize, long count) {
        when(mockTagRepository.count()).thenReturn(count);

        assertThrows(PaginationException.class, () -> {
            tagService.getTagsByPartName("test", pageNumber, pageSize);
        });
    }

    @Test
    public void testUpdateTags_emptyList() {
        List<TagToCreate> updateTags = new ArrayList<>();

        List<Tag> expected = new ArrayList<>();

        List<Tag> actual = tagService.updateTags(updateTags);

        assertEquals(expected, actual);
    }

    @Test
    public void testUpdateTags_withTags() {
        List<TagToCreate> updateTags = new ArrayList<>();
        TagToCreate tagToCreate = new TagToCreate();
        tagToCreate.setName("test");
        updateTags.add(tagToCreate);

        TagToCreate tagToCreate2 = new TagToCreate();
        tagToCreate2.setName("test2");
        updateTags.add(tagToCreate2);

        List<Tag> tags = new ArrayList<>();
        Tag tag = Tag.builder().name("test").build();
        Tag tag2 = Tag.builder().name("test2").build();
        tags.add(tag);
        tags.add(tag2);

        when(mockTagRepository.findTagByNameIgnoreCase("test")).thenReturn(Optional.of(tag));
        when(mockTagRepository.findTagByNameIgnoreCase("test2")).thenReturn(Optional.empty());
        when(mockTagRepository.save(Tag.builder().name("test2").build())).thenReturn(tag2);

        List<Tag> actual = tagService.updateTags(updateTags);

        assertTrue(actual.containsAll(tags));
        assertEquals(actual.size(), tags.size());
    }

    private static Stream<Arguments> inValidPaginationParams() {
        return Stream.of(
                arguments(2, 2, 2),
                arguments(2, 10, 10),
                arguments(2, 1, 1)
        );
    }
}