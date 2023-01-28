package com.epam.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private News news;

    private LocalDateTime created;

    private LocalDateTime modified;
}
