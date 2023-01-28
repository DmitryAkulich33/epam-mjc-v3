package com.epam.controller;

import com.epam.model.dto.NewsDto;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.Positive;
import java.util.List;

public interface NewsController {
    ResponseEntity<List<NewsDto>> getAllNews(@Positive Integer pageNumber, @Positive Integer pageSize);

}
