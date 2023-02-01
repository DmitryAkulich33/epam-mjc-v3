package com.epam.controller;

import com.epam.model.dto.TagDto;
import com.epam.model.dto.TagToCreate;
import com.epam.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/tags")
public class TagControllerImpl implements TagController {
    private final TagService tagService;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TagDto>> getAllTags(@RequestParam(defaultValue = "1") @Positive Integer pageNumber,
                                                   @RequestParam(defaultValue = "5") @Positive Integer pageSize) {
        List<TagDto> tags = tagService.getAllTags(pageNumber, pageSize);

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{tagId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TagDto> getTagById(@PathVariable("tagId") @Positive Long tagId) {
        return new ResponseEntity<>(tagService.getTagDtoById(tagId), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{tagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTagById(@PathVariable("tagId") @Positive Long tagId) {
        tagService.deleteTagById(tagId);
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TagDto> createTag(@RequestBody @Valid TagToCreate tagToCreate) {
        return new ResponseEntity<>(tagService.createTag(tagToCreate), HttpStatus.CREATED);
    }

    @Override
    @GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TagDto>> getTagsByPartName(@RequestParam @NotBlank String partName,
                                                          @RequestParam(defaultValue = "1") @Positive Integer pageNumber,
                                                          @RequestParam(defaultValue = "5") @Positive Integer pageSize) {
        List<TagDto> tags = tagService.getTagsByPartName(partName.trim(), pageNumber, pageSize);

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }
}
