package com.epam.lab.controller;

import com.epam.lab.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news/{newsId}")
public class TagController {
    @Autowired
    TagService tagService;

    @PostMapping(value = "/tags")
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

    @DeleteMapping(value = "/tags/{tagId}")
    public String deleteTag(@PathVariable("newsId") Long newsId,
                            @PathVariable("tagId") Long tagId) {
        return "{OK}";
    }
}
