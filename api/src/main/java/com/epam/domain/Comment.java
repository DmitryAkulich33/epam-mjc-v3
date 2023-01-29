package com.epam.domain;

import com.epam.dao.audit.AuditCommentListener;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditCommentListener.class)
public class Comment extends BaseEntity {
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private News news;

    private LocalDateTime created;

    private LocalDateTime modified;
}
