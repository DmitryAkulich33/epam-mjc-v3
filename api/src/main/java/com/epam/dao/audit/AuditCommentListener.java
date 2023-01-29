package com.epam.dao.audit;

import com.epam.domain.Comment;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class AuditCommentListener {
    @PrePersist
    public void createComment(Comment comment) {
        LocalDateTime createdDate = LocalDateTime.now();
        comment.setCreated(createdDate);
        comment.setModified(createdDate);
    }

    @PreUpdate
    public void updateComment(Comment comment) {
        comment.setModified(LocalDateTime.now());
    }
}
