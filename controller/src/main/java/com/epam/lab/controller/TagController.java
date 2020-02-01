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

    @GetMapping(value = "/tags/{id}")
    public Tag readTag(@PathVariable("id") Long id) {
        return tagService.read(new Tag().setId(id));
    }

    @PutMapping(value = "/tags/{id}")
    public void updateTag(@PathVariable("id") Long id,
                          @RequestBody Tag tag) {
        tagService.update(tag.setId(id));
    }

    @DeleteMapping(value = "/tags/{id}")
    public void deleteTag(@PathVariable("id") Long id) {
        tagService.delete(new Tag().setId(id));
    }

    @GetMapping(value = "/news/{id}/tags")
    public List<String> readNewsTags(@PathVariable("id") Long newsId) {
        //return tagService.read();
        return null;
    }
}
