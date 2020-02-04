package com.epam.lab.repository;

import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;

public interface NewsRepository extends EntityRepository<News> {
    void addAuthor(News news, Author author);
    void addTag(News news, Tag tag);
}
