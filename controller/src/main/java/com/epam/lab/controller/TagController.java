package com.epam.lab.controller;

import com.epam.lab.dto.TagDTO;
import com.epam.lab.service.TagService;
import com.epam.lab.validation.NewEntity;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/tags")
    public TagDTO createTag(@Validated(NewEntity.class) @RequestBody TagDTO tag) {
        return tagService.create(tag);
    }

    @GetMapping(value = "/tags/{id}")
    public TagDTO readTag(@PathVariable("id") Long id) {
        return tagService.read(new TagDTO().setId(id));
    }

    @PutMapping(value = "/tags/{id}")
    public void updateTag(@PathVariable("id") Long id, @Valid @RequestBody TagDTO tag) {
        tagService.update(tag.setId(id));
    }

    @DeleteMapping(value = "/tags/{id}")
    public void deleteTag(@PathVariable("id") Long id) {
        tagService.delete(new TagDTO().setId(id));
    }
}
