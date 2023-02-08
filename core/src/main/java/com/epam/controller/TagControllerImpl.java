package com.epam.controller;

import com.epam.hateoas.assembler.impl.TagCollectionAssembler;
import com.epam.model.dto.TagDto;
import com.epam.model.dto.TagToCreate;
import com.epam.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
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
    private final TagCollectionAssembler tagCollectionAssembler;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<TagDto>> getAllTags(@RequestParam(defaultValue = "${default.pageNumber}") @Positive Integer pageNumber,
                                                              @RequestParam(defaultValue = "${default.pageSize}") @Positive Integer pageSize) {
        List<TagDto> tags = tagService.getAllTags(pageNumber, pageSize);
        CollectionModel<TagDto> model = tagCollectionAssembler.toCollectionModel(tags, pageNumber, pageSize, null, null);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{tagId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TagDto> getEntityById(@PathVariable("tagId") @Positive Long tagId) {
        return new ResponseEntity<>(tagService.getEntityById(tagId), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{tagId}")
    public ResponseEntity<TagDto> deleteEntityById(@PathVariable("tagId") @Positive Long tagId) {
        tagService.deleteEntityById(tagId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TagDto> createTag(@RequestBody @Valid TagToCreate tagToCreate) {
        return new ResponseEntity<>(tagService.createTag(tagToCreate), HttpStatus.CREATED);
    }

    @Override
    @GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<TagDto>> getTagsByPartName(@RequestParam @NotBlank String partName,
                                                                     @RequestParam(defaultValue = "${default.pageNumber}") @Positive Integer pageNumber,
                                                                     @RequestParam(defaultValue = "${default.pageSize}") @Positive Integer pageSize) {
        List<TagDto> tags = tagService.getTagsByPartName(partName.trim(), pageNumber, pageSize);
        CollectionModel<TagDto> model = tagCollectionAssembler.toCollectionModel(tags, pageNumber, pageSize, null, null);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
