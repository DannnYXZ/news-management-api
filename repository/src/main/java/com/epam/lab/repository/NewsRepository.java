package com.epam.lab.repository;

import com.epam.lab.dto.Author;
import com.epam.lab.dto.News;
import com.epam.lab.dto.Tag;

public interface NewsRepository extends EntityRepository<News> {
    void addAuthor(News news, Author author);
    void addTag(News news, Tag tag);
}
