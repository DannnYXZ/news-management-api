package com.epam.lab.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news/{newsId}")
public class TagController {
    @GetMapping(value = "/tags")
    public String createTags() {
        return "{OK}";
    }

    @GetMapping(value = "/tags")
    public String readAllTags(@PathVariable("newsId") Long newsId) {
        return "{tags}";
    }

    @GetMapping(value = "/tags/{tagId}")
    public String readTag(@PathVariable("newsId") Long newsId,
                          @PathVariable("tagId") Long tagId) {
        return "{tag" + tagId + "}";
    }

    @PutMapping(value = "/tags/{tagId}")
    public String updateTag(@PathVariable("newsId") Long newsId,
                            @PathVariable("tagId") Long tagId) {
        return "{news}";
    }

    @PostMapping(value = "/tags/{tagId}")
    public String deleteNews(@PathVariable("newsId") Long newsId,
                             @PathVariable("tagId") Long id) {
        return "{OK}";
    }

}
