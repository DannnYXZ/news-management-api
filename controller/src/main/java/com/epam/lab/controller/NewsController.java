package com.epam.lab.controller;

import com.epam.lab.dto.*;
import com.epam.lab.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class NewsController {

    @Autowired
    NewsService newsService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/news")
    public NewsDTO createNews(@RequestBody NewsDTO news) {
        NewsDTO identifiedNews = newsService.create(news);
        return identifiedNews;
    }

    @GetMapping(value = "/news")
    public List<NewsDTO> readNews(@RequestParam(required = false) List<String> tags,
                                  @RequestParam(required = false) String author,
                                  @RequestParam(required = false) SortCriteriaDTO sort) {
        List<NewsDTO> news = newsService.readNews(new SearchCriteriaDTO()
                .setSortCriteria(sort)
                .setAuthor(new AuthorDTO().setName(author))
                .setTags(tags != null
                        ? tags.stream().map(tag -> new TagDTO().setName(tag)).collect(Collectors.toList())
                        : null));
        return news;
    }

    @GetMapping(value = "/news/{id}")
    public NewsDTO readNews(@PathVariable("id") Long id) {
        NewsDTO news = newsService.read(new NewsDTO().setId(id));
        return news;
    }

    @PutMapping(value = "/news/{id}")
    public void updateNews(@PathVariable("id") Long id,
                           @RequestBody NewsDTO news) {
        newsService.update(news.setId(id));
    }

    @DeleteMapping(value = "/news/{id}")
    public void deleteNews(@PathVariable("id") Long id) {
        newsService.delete(new NewsDTO().setId(id));
    }

    @GetMapping(value = "/news/count")
    public long countNews() {
        return newsService.countNews();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/news/{newsId}/author/{authorId}")
    public void addAuthor(@PathVariable("newsId") Long newsId,
                          @PathVariable("authorId") Long authorId) {
        newsService.addAuthor(new NewsDTO().setId(newsId), new AuthorDTO().setId(authorId));
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/news/{newsId}/tags/{tagId}")
    public void addTag(@PathVariable("newsId") Long newsId,
                       @PathVariable("tagId") Long tagId) {
        newsService.addTag(new NewsDTO().setId(newsId), new TagDTO().setId(tagId));
    }
}
