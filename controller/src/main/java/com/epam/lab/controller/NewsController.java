package com.epam.lab.controller;

import com.epam.lab.dto.Author;
import com.epam.lab.dto.News;
import com.epam.lab.dto.SearchCriteria;
import com.epam.lab.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<News> readNews(@RequestBody SearchCriteria criteria) { // TODO: search criteria
        List<News> news = newsService.readNews(criteria);
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

    @PostMapping(value = "/news/{id}/author")
    public void addAuthor(@PathVariable("id") Long id,
                          @RequestBody Author author) {
        newsService.addAuthor(new News().setId(id), author);
    }
}
