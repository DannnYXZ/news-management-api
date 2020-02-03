package com.epam.lab.controller;

import com.epam.lab.dto.*;
import com.epam.lab.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class NewsController {

    @Autowired
    NewsService newsService;

    @PostMapping(value = "/news")
    public News createNews(@RequestBody News news) {
        News identifiedNews = newsService.create(news);
        return identifiedNews;
    }

    @GetMapping(value = "/news")
    public List<News> readNews(@RequestParam(required = false) List<String> tags,
                               @RequestParam(required = false) String author,
                               @RequestParam(required = false) SortCriteria sort) {
        List<News> news = newsService.readNews(new SearchCriteria()
                .setSortCriteria(sort)
                .setAuthor(new Author().setName(author))
                .setTags(tags != null
                        ? tags.stream().map(tag -> new Tag().setName(tag)).collect(Collectors.toList())
                        : null));
        return news;
    }

    @GetMapping(value = "/news/{id}")
    public News readNews(@PathVariable("id") Long id) {
        News news = newsService.read(new News().setId(id));
        return news;
    }

    @PutMapping(value = "/news/{id}")
    public void updateNews(@PathVariable("id") Long id,
                           @RequestBody News news) {
        newsService.update(news.setId(id));
    }

    @DeleteMapping(value = "/news/{id}")
    public void deleteNews(@PathVariable("id") Long id) {
        newsService.delete(new News().setId(id));
    }

    @GetMapping(value = "/news/count")
    public long countNews() {
        return newsService.countNews();
    }

    @PostMapping(value = "/news/{newsId}/author/{authorId}")
    public void addAuthor(@PathVariable("newsId") Long newsId,
                          @PathVariable("authorId") Long authorId) {
        newsService.addAuthor(new News().setId(newsId), new Author().setId(authorId));
    }

    @PostMapping(value = "/news/{newsId}/tags/{tagId}")
    public void addTag(@PathVariable("newsId") Long newsId,
                       @PathVariable("tagId") Long tagId) {
        newsService.addTag(new News().setId(newsId), new Tag().setId(tagId));
    }
}
