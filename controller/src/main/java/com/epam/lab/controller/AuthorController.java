package com.epam.lab.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news/{newsId}/author")
public class AuthorController {
    @PostMapping
    public String createAuthor(@PathVariable("newsId") Long newsId) {
        return "{OK}";
    }

    @GetMapping
    public String readAuthor(@PathVariable("newsId") Long newsId) {
        return "{news}";
    }

    @PutMapping
    public String updateAuthor(@PathVariable("newsId") Long newsId) {
        return "{news}";
    }

    @DeleteMapping
    public String deleteAuthor(@PathVariable("newsId") Long newsId) {
        return "{OK}";
    }

}
