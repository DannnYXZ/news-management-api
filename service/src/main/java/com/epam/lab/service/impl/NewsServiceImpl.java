package com.epam.lab.service.impl;

import com.epam.lab.dto.*;
import com.epam.lab.exception.EntityNotFoundException;
import com.epam.lab.exception.TagsLinkageException;
import com.epam.lab.model.*;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.service.NewsService;
import com.epam.lab.specification.impl.NewsByIdSpecification;
import com.epam.lab.specification.impl.NewsBySearchCriteriaSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private NewsRepository newsRepository;
    private ModelMapper modelMapper;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public long countNews() {
        return newsRepository.count();
    }

    @Override
    public NewsDTO create(NewsDTO element) {
        News news = modelMapper.map(element, News.class);
        Date nowDate = new Date();
        news.setModificationDate(nowDate).setCreationDate(nowDate);
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
        news.sort(SortCriteriaComparator.getComparator(criteria));
    }

    @Override
    public List<NewsDTO> readNews(SearchCriteriaDTO criteria) {
        SearchCriteria searchCriteria = modelMapper.map(criteria, SearchCriteria.class);
        List<News> news = newsRepository.query(new NewsBySearchCriteriaSpecification(searchCriteria));
        SortCriteriaDTO sortCriteriaDTO = criteria.getSort();
        if (sortCriteriaDTO != null) {
            sortByCriteria(news, modelMapper.map(sortCriteriaDTO, SortCriteria.class));
        }
        return news.stream().map(x -> modelMapper.map(x, NewsDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void linkAuthor(NewsDTO news, AuthorDTO author) {
        newsRepository.linkAuthor(modelMapper.map(news, News.class), modelMapper.map(author, Author.class));
    }

    @Override
    public void unlinkAuthor(NewsDTO news, AuthorDTO author) {
        newsRepository.unlinkAuthor(modelMapper.map(news, News.class), modelMapper.map(author, Author.class));
    }

    private void processTags(BiConsumer<News, Tag> function, NewsDTO news, List<TagDTO> tags) {
        TagsLinkageException compoundException = new TagsLinkageException();
        for (TagDTO tag : tags) {
            try {
                function.accept(modelMapper.map(news, News.class), modelMapper.map(tag, Tag.class));
            } catch (Exception e) {
                compoundException.add(e);
            }
        }
        if (!compoundException.getExceptions().isEmpty()) {
            throw compoundException;
        }
    }

    @Override
    public void linkTags(NewsDTO news, List<TagDTO> tags) {
        processTags(newsRepository::linkTag, news, tags);
    }

    @Override
    public void unlinkTags(NewsDTO news, List<TagDTO> tags) {
        processTags(newsRepository::unlinkTag, news, tags);
    }
}
