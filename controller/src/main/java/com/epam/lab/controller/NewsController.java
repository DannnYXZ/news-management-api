package com.epam.lab.controller;

import com.epam.lab.dto.News;
import com.epam.lab.dto.SearchCriteria;
import com.epam.lab.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class NewsController {

    @Autowired
    NewsService newsService;

    @PostMapping(value = "/news")
    public News createNews(@RequestBody News news) {
        return news;
    }

    @GetMapping(value = "/news")
    public String readAllNews(@RequestBody SearchCriteria criteria) {
        return "{news}";
    }

    @GetMapping(value = "/news/{newsId}")
    public String readNews(@PathVariable("newsId") Long newsId) {
        return "{news}";
    }

    @PutMapping(value = "/news/{newsId}")
    public String updateNews(@PathVariable("newsId") Long newsId) {
        return "{news}";
    }

    @DeleteMapping(value = "/news/{newsId}")
    public String deleteNews(@PathVariable("newsId") Long newsId) {
        return "{OK}";
    }

    @GetMapping(value = "/news/count")
    public int countNews() {
        return 4;
    }
}
