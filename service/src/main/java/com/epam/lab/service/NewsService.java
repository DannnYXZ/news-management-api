package com.epam.lab.service;

import com.epam.lab.dto.Author;
import com.epam.lab.dto.News;
import com.epam.lab.dto.SearchCriteria;
import com.epam.lab.dto.Tag;

import java.util.List;

public interface NewsService extends CrudService<News> {
    void addAuthor(News news, Author author);
    void addTag(News news, Tag tag);
    long countNews();
    List<News> readNews(SearchCriteria criteria);
}
