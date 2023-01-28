package com.epam.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity(name = "news")
@NoArgsConstructor
@AllArgsConstructor
public class News extends BaseEntity {
    @Column(unique = true)
    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    private LocalDateTime created;

    private LocalDateTime modified;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "news_tag",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();
}
