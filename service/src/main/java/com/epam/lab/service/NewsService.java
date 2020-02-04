package com.epam.lab.service;

import com.epam.lab.dto.AuthorDTO;
import com.epam.lab.dto.NewsDTO;
import com.epam.lab.dto.SearchCriteriaDTO;
import com.epam.lab.dto.TagDTO;

import java.util.List;


public interface NewsService extends CrudService<NewsDTO> {
    void addAuthor(NewsDTO news, AuthorDTO author);
    void addTag(NewsDTO news, TagDTO tag);
    long countNews();
    List<NewsDTO> readNews(SearchCriteriaDTO criteria);
}
