package com.epam.lab.service.impl;

import com.epam.lab.dto.AuthorDTO;
import com.epam.lab.dto.NewsDTO;
import com.epam.lab.dto.SearchCriteriaDTO;
import com.epam.lab.dto.TagDTO;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.SearchCriteria;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.service.NewsService;
import com.epam.lab.specification.impl.NewsByIdSpecification;
import com.epam.lab.specification.impl.NewsBySearchCriteriaSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public long countNews() {
        return newsRepository.count();
    }

    @Override
    public NewsDTO create(NewsDTO element) {
        News news = modelMapper.map(element, News.class);
        news.setModificationDate(element.getCreationDate());
        News identifiedNews = newsRepository.create(news);
        return modelMapper.map(identifiedNews, NewsDTO.class);
    }

    @Override
    public NewsDTO read(NewsDTO element) {
        List<News> newsList = newsRepository.query(new NewsByIdSpecification(element.getId()));
        return modelMapper.map(newsList.get(0), NewsDTO.class);
    }

    @Override
    public void update(NewsDTO element) {
        newsRepository.update(modelMapper.map(element, News.class));
    }

    @Override
    public void delete(NewsDTO element) {
        newsRepository.delete(modelMapper.map(element, News.class));
    }

    @Override
    public List<NewsDTO> readNews(SearchCriteriaDTO criteria) {
        SearchCriteria searchCriteria = modelMapper.map(criteria, SearchCriteria.class);
        List<News> news = newsRepository.query(new NewsBySearchCriteriaSpecification(searchCriteria));
        return news.stream().map(x -> modelMapper.map(x, NewsDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void addAuthor(NewsDTO news, AuthorDTO author) {
        newsRepository.addAuthor(modelMapper.map(news, News.class), modelMapper.map(author, Author.class));
    }

    @Override
    public void addTag(NewsDTO news, TagDTO tag) {
        newsRepository.addTag(modelMapper.map(news, News.class), modelMapper.map(tag, Tag.class));
    }
}
