package com.epam.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
}
