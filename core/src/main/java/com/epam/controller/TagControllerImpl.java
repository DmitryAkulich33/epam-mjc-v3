package com.epam.controller;

import com.epam.hateoas.assembler.impl.TagCollectionAssembler;
import com.epam.model.dto.TagDto;
import com.epam.model.dto.TagToCreate;
import com.epam.service.TagService;
import io.swagger.annotations.*;
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
@Api(tags = "API functionality of working with Tag")
public class TagControllerImpl implements TagController {
    private final TagService tagService;
    private final TagCollectionAssembler tagCollectionAssembler;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all tags", notes = "The endpoint for getting all tags")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "Accept-Language", example = "en", dataType = "String", paramType = "header")
    })
    public ResponseEntity<CollectionModel<TagDto>> getAllTags(@RequestParam(defaultValue = "${default.pageNumber}") @Positive int pageNumber,
                                                              @RequestParam(defaultValue = "${default.pageSize}") @Positive int pageSize) {
        List<TagDto> tags = tagService.getAllTags(pageNumber, pageSize);
        CollectionModel<TagDto> model = tagCollectionAssembler.toCollectionModel(tags, pageNumber, pageSize, null, null);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{tagId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get tag by id", notes = "The endpoint for getting a tag by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tagId", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "Accept-Language", example = "en", dataType = "String", paramType = "header")
    })
    public ResponseEntity<TagDto> getEntityById(@PathVariable("tagId") @Positive Long tagId) {
        return new ResponseEntity<>(tagService.getEntityById(tagId), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{tagId}")
    @ApiOperation(value = "Delete tag by id", notes = "The endpoint for deleting a tag by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tagId", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "Accept-Language", example = "en", dataType = "String", paramType = "header")
    })
    public ResponseEntity<TagDto> deleteEntityById(@PathVariable("tagId") @Positive Long tagId) {
        tagService.deleteEntityById(tagId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a tag", notes = "The endpoint for creating tag")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tagToCreate", dataType = "TagToCreate", required = true),
            @ApiImplicitParam(name = "Accept-Language", example = "en", dataType = "String", paramType = "header")
    })
    public ResponseEntity<TagDto> createTag(@RequestBody @Valid TagToCreate tagToCreate) {
        return new ResponseEntity<>(tagService.createTag(tagToCreate), HttpStatus.CREATED);
    }

    @Override
    @GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Filter tags by part of name", notes = "The endpoint to filter all tags by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "partName", dataType = "String", required = true),
            @ApiImplicitParam(name = "Accept-Language", example = "en", dataType = "String", paramType = "header")
    })
    public ResponseEntity<CollectionModel<TagDto>> getTagsByPartName(@RequestParam @NotBlank String partName,
                                                                     @RequestParam(defaultValue = "${default.pageNumber}") @Positive int pageNumber,
                                                                     @RequestParam(defaultValue = "${default.pageSize}") @Positive int pageSize) {
        List<TagDto> tags = tagService.getTagsByPartName(partName.trim(), pageNumber, pageSize);
        CollectionModel<TagDto> model = tagCollectionAssembler.toCollectionModel(tags, pageNumber, pageSize, null, null);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
