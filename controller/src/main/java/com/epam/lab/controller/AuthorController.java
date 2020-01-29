package com.epam.lab.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news/{newsId}")
public class AuthorController {
    @RequestMapping(value = "/author", method = RequestMethod.POST)
    public String createAuthor(@PathVariable("newsId") Long newsId) {
        return "{OK}";
    }

    @RequestMapping(value = "/author", method = RequestMethod.GET)
    public String readAuthor(@PathVariable("newsId") Long newsId) {
        return "{news}";
    }

    @RequestMapping(value = "/author", method = RequestMethod.PUT)
    public String updateAuthor(@PathVariable("newsId") Long newsId) {
        return "{news}";
    }

    @RequestMapping(value = "/author", method = RequestMethod.POST)
    public String deleteAuthor(@PathVariable("newsId") Long newsId) {
        return "{OK}";
    }

}
