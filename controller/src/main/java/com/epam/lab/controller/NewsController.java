package com.epam.lab.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {
    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public String createNews() {
        return "{OK}";
    }

//    @RequestMapping(value = "/news", method = RequestMethod.GET)
//    public String readAllNews(@PathVariable("id") SearchCriteria id) {
//        return "{news}";
//    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
    public String readNews(@PathVariable("id") Long id) {
        return "{news}";
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.PUT)
    public String updateNews() {
        return "{news}";
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.DELETE)
    public String deleteNews(@PathVariable("id") Long id) {
        return "{OK}";
    }

    @RequestMapping(value = "/news/count", method = RequestMethod.GET)
    public int countNews() {
        return 4;
    }
}
