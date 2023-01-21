package com.epam.controller;

import com.epam.dao.TagRepository;
import com.epam.domain.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tags")
@AllArgsConstructor
public class TagControllerImpl {
    private final TagRepository tagRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = (List<Tag>) tagRepository.findAll();

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

}
