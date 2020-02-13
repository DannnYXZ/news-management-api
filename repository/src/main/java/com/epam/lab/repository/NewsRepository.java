package com.epam.lab.repository;

import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;

public interface NewsRepository extends EntityRepository<News> {

    void linkAuthor(News news, Author author);

    void unlinkAuthor(News news, Author author);

    void linkTag(News news, Tag tag);

    void unlinkTag(News news, Tag tag);
}
