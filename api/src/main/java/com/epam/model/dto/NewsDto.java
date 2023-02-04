package com.epam.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class NewsDto extends RepresentationModel<NewsDto> {
    private Long id;
    private String title;
    private String content;
    private AuthorDto author;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modified;
    private List<TagDto> tags;
    private List<CommentDto> comments;
}
