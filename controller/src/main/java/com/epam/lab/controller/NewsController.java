package com.epam.lab.controller;

import com.epam.lab.dto.SearchCriteria;
import org.springframework.web.bind.annotation.*;

@RestController
public class NewsController {
    @PostMapping(value = "/news")
    public String createNews() {
        return "{OK}";
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
