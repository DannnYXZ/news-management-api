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
        News createdNews = newsService.create(news);
        return createdNews;
    }

    @GetMapping(value = "/news")
    public List<News> readNews(@RequestBody SearchCriteria criteria) {
        List<News> news = newsService.getNews(criteria);
        return news;
    }

    @GetMapping(value = "/news/{newsId}")
    public News readNews(@PathVariable("newsId") Long newsId) {
        News news = newsService.read(new News().setId(newsId));
        return news;
    }

    @PutMapping(value = "/news/{newsId}")
    public News updateNews(@PathVariable("newsId") Long newsId,
                           @RequestBody News news) {
        News updated = newsService.update(news.setId(newsId));
        return updated;
    }

    @DeleteMapping(value = "/news/{newsId}")
    public void deleteNews(@PathVariable("newsId") Long newsId,
                           @RequestBody News news) {
        newsService.delete(news.setId(newsId));
    }

    @GetMapping(value = "/news/count")
    public int countNews() {
        return newsService.countNews();
    }

    @PostMapping(value = "/news/{newsId}/author")
    public void addAuthor(@PathVariable("newsId") Long newsId,
                          @RequestBody Author author) {
        newsService.addAuthor(new News().setId(newsId), author);
    }
}
