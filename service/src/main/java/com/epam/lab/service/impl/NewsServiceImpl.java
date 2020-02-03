package com.epam.lab.service.impl;

import com.epam.lab.dto.Author;
import com.epam.lab.dto.News;
import com.epam.lab.dto.SearchCriteria;
import com.epam.lab.dto.Tag;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.service.NewsService;
import com.epam.lab.specification.impl.NewsIdSpecification;
import com.epam.lab.specification.impl.NewsSearchCriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public long countNews() {
        return newsRepository.count();
    }

    @Override
    public News create(News element) {
        element.setModificationDate(element.getCreationDate());
        News identifiedNews = newsRepository.create(element);
        return identifiedNews;
    }

    @Override
    public News read(News element) {
        List<News> news = newsRepository.query(new NewsIdSpecification(element.getId()));
        return news.get(0);
    }

    @Override
    public void update(News element) {
        newsRepository.update(element);
    }

    @Override
    public void delete(News element) {
        newsRepository.delete(element);
    }

    @Override
    public List<News> readNews(SearchCriteria criteria) {
        return newsRepository.query(new NewsSearchCriteriaSpecification(criteria));
    }

    @Override
    public void addAuthor(News news, Author author) {
        newsRepository.addAuthor(news, author);
    }

    @Override
    public void addTag(News news, Tag tag) {
        newsRepository.addTag(news, tag);
    }
}
