package com.epam.lab.service.impl;

import com.epam.lab.dto.Author;
import com.epam.lab.dto.News;
import com.epam.lab.dto.SearchCriteria;
import com.epam.lab.dto.Tag;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NewsServiceImpl implements NewsService {

    @Autowired
    private EntityRepository<News> newsRepository;

    public void addAuthor(News news, Author author) {
    }

    public void addTags(List<Tag> tags) {

    }

    public int countNews() {
        return 0;
    }

    public List<News> getNews(SearchCriteria criteria) {
        return null;
    }

    public News create(News element) {
        newsRepository.add(element);
        return element;
    }

    public News read(News element) {
        return null;
    }

    public News update(News element) {
        return null;
    }

    public void delete(News element) {

    }
}
