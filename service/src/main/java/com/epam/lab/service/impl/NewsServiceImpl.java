package com.epam.lab.service.impl;

import com.epam.lab.dto.Author;
import com.epam.lab.dto.News;
import com.epam.lab.dto.SearchCriteria;
import com.epam.lab.dto.Tag;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.service.NewsService;
import com.epam.lab.specification.impl.NewsIdSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NewsServiceImpl implements NewsService {

    @Autowired
    private EntityRepository<News> newsRepository;

    public int countNews() {
        return 0;
    }

    public News create(News element) {
        element.setModificationDate(element.getCreationDate());
        News identifiedNews = newsRepository.save(element);
        return identifiedNews;
    }

    public News read(News element) {
        List<News> news = newsRepository.query(new NewsIdSpecification(element.getId()));
        return news.get(0);
    }

    public void update(News element) {
        newsRepository.update(element);
    }

    public void delete(News element) {

    }

    public List<News> readNews(SearchCriteria criteria) {
        return null;
    }

    public void addAuthor(News news, Author author) {
    }

    public void addTags(List<Tag> tags) {

    }
}
