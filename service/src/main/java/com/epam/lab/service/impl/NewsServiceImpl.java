package com.epam.lab.service.impl;

import com.epam.lab.dto.*;
import com.epam.lab.exception.EntityNotFoundException;
import com.epam.lab.model.*;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.service.NewsService;
import com.epam.lab.specification.impl.NewsByIdSpecification;
import com.epam.lab.specification.impl.NewsBySearchCriteriaSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
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
        if (!newsList.isEmpty()) {
            return modelMapper.map(newsList.get(0), NewsDTO.class);
        } else {
            throw new EntityNotFoundException("No such news.");
        }
    }

    @Override
    public void update(NewsDTO element) {
        newsRepository.update(modelMapper.map(element, News.class));
    }

    @Override
    public void delete(NewsDTO element) {
        newsRepository.delete(modelMapper.map(element, News.class));
    }

    private void sortByCriteria(List<News> news, SortCriteria criteria) {
        news.sort((a, b) -> {
            if (criteria == SortCriteria.AUTHOR) {
                Author auth1 = a.getAuthor(), auth2 = b.getAuthor();
                return auth1 == null ? 1 : auth1.compareTo(auth2);
            }
            if (criteria == SortCriteria.TAG) {
                List<Tag> tags1 = a.getTags(), tags2 = b.getTags();
                return tags1 == null ? 1 : tags2 == null ? -1 : tags2.size() - tags1.size();
            }
            if (criteria == SortCriteria.DATE) {
                Date date1 = a.getCreationDate(), date2 = b.getCreationDate();
                return date1 == null ? 1 : date1.compareTo(date2);
            }
            return 0;
        });
    }

    @Override
    public List<NewsDTO> readNews(SearchCriteriaDTO criteria) {
        SearchCriteria searchCriteria = modelMapper.map(criteria, SearchCriteria.class);
        List<News> news = newsRepository.query(new NewsBySearchCriteriaSpecification(searchCriteria));
        SortCriteriaDTO sortCriteriaDTO = criteria.getSortCriteria();
        if (sortCriteriaDTO != null) {
            sortByCriteria(news, modelMapper.map(sortCriteriaDTO, SortCriteria.class));
        }
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
