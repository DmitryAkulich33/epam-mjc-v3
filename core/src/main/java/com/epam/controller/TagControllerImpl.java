package com.epam.controller;

import com.epam.model.dto.TagDto;
import com.epam.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/tags")
public class TagControllerImpl implements TagController {
    private final TagService service;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TagDto>> getAllTags(@RequestParam(defaultValue = "1") @Positive Integer pageNumber,
                                                   @RequestParam(defaultValue = "3") @Positive Integer pageSize) {
        List<TagDto> tags = service.getAllTags(pageNumber, pageSize);

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

}
