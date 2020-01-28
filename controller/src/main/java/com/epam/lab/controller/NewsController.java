package com.epam.lab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class NewsController {
    @GetMapping(value = "hello")
    public String sendNews() {
        return "{news1, news2}";
    }
}
