package com.epam.dao;

import com.epam.domain.News;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends AbstractRepository<News> {
    Optional<News> findNewsByTitleIgnoreCase(String title);
}
