package com.epam.controller;

import com.epam.model.dto.TagDto;
import com.epam.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TagControllerImpl implements TagController {
    private final TagService service;

    @Override
    public ResponseEntity<List<TagDto>> getAllTags() {
        List<TagDto> tags = service.getAllTags();

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }
}
