package com.epam.lab.controller;

import com.epam.lab.dto.Tag;
import com.epam.lab.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @PostMapping(value = "/tags")
    public Tag createTag(@RequestBody Tag tag) {
        Tag identifiedTag = tagService.create(tag);
        return identifiedTag;
    }

    @GetMapping(value = "/tags/{tagId}")
    public Tag readTag(@PathVariable("tagId") Long tagId) {
        return tagService.read(new Tag().setId(tagId));
    }

    @PutMapping(value = "/tags/{tagId}")
    public String updateTag(@PathVariable("tagId") Long tagId) {
        return "{news}";
    }

    @DeleteMapping(value = "/tags/{tagId}")
    public void deleteTag(@PathVariable("tagId") Long tagId) {
        tagService.delete(new Tag().setId(tagId));
    }

    @GetMapping(value = "/news/{newsId}/tags")
    public List<String> readNewsTags(@PathVariable("newsId") Long newsId) {
        return null;
    }
}
