package com.epam.lab.service;

import com.epam.lab.dto.AuthorDTO;
import com.epam.lab.dto.NewsDTO;
import com.epam.lab.dto.SearchCriteriaDTO;
import com.epam.lab.dto.TagDTO;

import java.util.List;


public interface NewsService extends CrudService<NewsDTO> {
    void linkAuthor(NewsDTO news, AuthorDTO author);
    void unlinkAuthor(NewsDTO news, AuthorDTO author);
    void linkTags(NewsDTO news, List<TagDTO> tags);
    void unlinkTags(NewsDTO news, List<TagDTO> tags);
    long countNews();
    List<NewsDTO> readNews(SearchCriteriaDTO criteria);
}
