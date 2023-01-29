package com.epam.dao.audit;

import com.epam.domain.News;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AuditNewsListener {
    @PrePersist
    public void createNews(News news) {
        LocalDateTime createdDate = LocalDateTime.now();
        news.setCreated(createdDate);
        news.setModified(createdDate);
        news.setComments(new ArrayList<>());
    }

    @PreUpdate
    public void updateNews(News news) {
        news.setModified(LocalDateTime.now());
    }
}
