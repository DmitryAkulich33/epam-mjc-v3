package com.epam.controller;

import com.epam.exception.ExceptionResponse;
import com.epam.exception.TagAlreadyExistsException;
import com.epam.exception.TagNotFoundException;
import com.epam.hateoas.assembler.impl.TagCollectionAssembler;
import com.epam.model.dto.TagDto;
import com.epam.model.dto.TagToCreate;
import com.epam.service.TagServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TagControllerImplWebTest {
    private final static String BASE_URL = "/api/v1/tags";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagServiceImpl mockTagService;

    @MockBean
    private TagCollectionAssembler mockTagCollectionAssembler;

    @Autowired
    private ObjectMapper objectMapper;

    private ExceptionResponse customException_40401;
    private ExceptionResponse customException_40005;
    private ExceptionResponse validationException_40005;
    private ExceptionResponse validationException_40007;
    private ExceptionResponse requestException_40007;

    private TagDto tagDto1;
    private List<TagDto> tags;


    @BeforeEach
    public void setUp() {
        customException_40401 = new ExceptionResponse("Error message", "40401");
        customException_40005 = new ExceptionResponse("Error message", "40005");
        validationException_40005 = new ExceptionResponse("Entered data is not valid", "40005");
        validationException_40007 = new ExceptionResponse("The message is not readable", "40007");
        requestException_40007 = new ExceptionResponse("Parameter of request is missed", "40007");
        tagDto1 = new TagDto();
        TagDto tagDto2 = new TagDto();
        tagDto1.setName("test1");
        tagDto1.setId(1L);
        tagDto2.setName("test2");
        tagDto2.setId(2L);
        tags = List.of(tagDto1, tagDto2);

    }

    @Test
    public void testDeleteTag_204() throws Exception {
        doNothing().when(mockTagService).deleteEntityById(anyLong());

        mockMvc
                .perform(delete(String.format("%s/1", BASE_URL)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteTag_404() throws Exception {
        doThrow(new TagNotFoundException("Error message")).when(mockTagService).deleteEntityById(anyLong());

        mockMvc
                .perform(delete(String.format("%s/1", BASE_URL)))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(is(objectMapper.writeValueAsString(customException_40401))));
    }

    @ParameterizedTest
    @ValueSource(longs = {-1, 0})
    public void testDeleteTag_400(Long tagId) throws Exception {
        mockMvc
                .perform(delete(String.format("%s/%d", BASE_URL, tagId)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(is(objectMapper.writeValueAsString(validationException_40005))));
    }

    @Test
    public void testDetTagById_200() throws Exception {
        when(mockTagService.getEntityById(anyLong())).thenReturn(tagDto1);

        mockMvc.perform(get(String.format("%s/1", BASE_URL)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("\"id\":1,\"name\":\"test1\"")));
    }

    @Test
    public void testGetTagById_404() throws Exception {
        when(mockTagService.getEntityById(anyLong())).thenThrow(new TagNotFoundException("Error message"));

        mockMvc.perform(get(String.format("%s/1", BASE_URL)))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(is(objectMapper.writeValueAsString(customException_40401))));
    }

    @ParameterizedTest
    @ValueSource(longs = {-1, 0})
    public void testGetTagById_400(Long tagId) throws Exception {
        mockMvc.perform(get(String.format("%s/%d", BASE_URL, tagId)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(is(objectMapper.writeValueAsString(validationException_40005))));
    }

    @Test
    public void testGetAllTags_defaultPagination_200() throws Exception {
        when(mockTagService.getAllTags(anyInt(), anyInt())).thenReturn(tags);
        when(mockTagCollectionAssembler.toCollectionModel(tags, 1, 10, null, null))
                .thenReturn(CollectionModel.of(tags));

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$._embedded.tagDtoList", hasSize(2)))
                .andExpect(content().string(containsString("\"id\":1,\"name\":\"test1\"")))
                .andExpect(content().string(containsString("\"id\":2,\"name\":\"test2\"")));
    }

    @ParameterizedTest
    @MethodSource("inValidPaginationParams")
    public void testGetAllTags_invalidPagination_400(Integer pageNumber, Integer pageSize) throws Exception {
        mockMvc.perform(get(String.format("%s?pageNumber=%d&pageSize=%d", BASE_URL, pageNumber, pageSize)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(is(objectMapper.writeValueAsString(validationException_40005))));

    }

    @Test
    public void testGetTagsByPartName() throws Exception {
        when(mockTagService.getTagsByPartName(anyString(), anyInt(), anyInt())).thenReturn(tags);
        when(mockTagCollectionAssembler.toCollectionModel(tags, 1, 10, null, null))
                .thenReturn(CollectionModel.of(tags));

        mockMvc.perform(get(String.format("%s/filter?partName=%s", BASE_URL, "test")))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$._embedded.tagDtoList", hasSize(2)))
                .andExpect(content().string(containsString("\"id\":1,\"name\":\"test1\"")))
                .andExpect(content().string(containsString("\"id\":2,\"name\":\"test2\"")));
    }

    @Test
    public void testGetTagsByPartName_duplicateTag_400() throws Exception {
        when(mockTagService.getTagsByPartName(anyString(), anyInt(), anyInt())).thenThrow(new TagAlreadyExistsException("Error message"));

        mockMvc.perform(get(String.format("%s/filter?partName=%s", BASE_URL, "test")))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(is(objectMapper.writeValueAsString(customException_40005))));
    }

    @ParameterizedTest
    @MethodSource("inValidPaginationParams")
    public void testGetTagsByPartName_invalidPagination_400(Integer pageNumber, Integer pageSize) throws Exception {
        mockMvc.perform(get(String.format("%s/filter?partName=%s&pageNumber=%d&pageSize=%d", BASE_URL, "test", pageNumber, pageSize)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(is(objectMapper.writeValueAsString(validationException_40005))));

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    public void testGetTagsByPartName_invalidSearchParamIsNotNull_400(String searchParam) throws Exception {
        mockMvc.perform(get(String.format("%s/filter?partName=%s", BASE_URL, searchParam)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(is(objectMapper.writeValueAsString(validationException_40005))));

    }

    @Test
    public void testGetTagsByPartName_SearchParamIsNull_400() throws Exception {
        mockMvc.perform(get(String.format("%s/filter", BASE_URL)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(is(objectMapper.writeValueAsString(requestException_40007))));

    }

    @Test
    public void testCreateTag_200() throws Exception {
        when(mockTagService.createTag(any())).thenReturn(tagDto1);

        TagToCreate tagToCreate = new TagToCreate();
        tagToCreate.setName("test1");

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tagToCreate)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("\"id\":1,\"name\":\"test1\"")));
    }

    @Test
    public void testCreateTag_nullBody_400() throws Exception {
        mockMvc.perform(post(BASE_URL))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(is(objectMapper.writeValueAsString(validationException_40007))));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "ab", " name", "name ", "abcdefghijklmnop", "nameMoreThan15symbols"})
    public void testCreateTag_invalidBody_400(String name) throws Exception {
        TagToCreate tagToCreate = new TagToCreate();
        tagToCreate.setName(name);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tagToCreate)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(is(objectMapper.writeValueAsString(validationException_40005))));
    }

    private static Stream<Arguments> inValidPaginationParams() {
        return Stream.of(
                arguments(-1, 1),
                arguments(1, -1),
                arguments(0, 1),
                arguments(1, 0)
        );
    }
}