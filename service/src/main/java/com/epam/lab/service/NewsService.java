package com.epam.lab.service;

import com.epam.lab.dto.Author;
import com.epam.lab.dto.News;
import com.epam.lab.dto.SearchCriteria;
import com.epam.lab.dto.Tag;

import java.util.List;

public interface NewsService extends CrudService<News> {
    void addAuthor(News news, Author author);
    void addTags(List<Tag> tags);
    int countNews();
    List<News> getNews(SearchCriteria criteria);
}
