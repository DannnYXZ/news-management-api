package com.epam.lab.controller;

import com.epam.lab.dto.TagDTO;
import com.epam.lab.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/tags")
    public TagDTO createTag(@RequestBody TagDTO tag) {
        TagDTO identifiedTag = tagService.create(tag);
        return identifiedTag;
    }

    @GetMapping(value = "/tags/{id}")
    public TagDTO readTag(@PathVariable("id") Long id) {
        return tagService.read(new TagDTO().setId(id));
    }

    @PutMapping(value = "/tags/{id}")
    public void updateTag(@PathVariable("id") Long id,
                          @RequestBody TagDTO tag) {
        tagService.update(tag.setId(id));
    }

    @DeleteMapping(value = "/tags/{id}")
    public void deleteTag(@PathVariable("id") Long id) {
        tagService.delete(new TagDTO().setId(id));
    }
}
