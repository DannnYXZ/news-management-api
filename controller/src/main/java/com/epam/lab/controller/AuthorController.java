package com.epam.lab.controller;

import com.epam.lab.dto.Author;
import com.epam.lab.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping(value = "/author")
    public Author createAuthor(@RequestBody Author author) {
        Author identifiedAuthor = authorService.create(author);
        return identifiedAuthor;
    }

    @GetMapping(value = "/author/{id}")
    public Author readAuthor(@PathVariable("id") Long id) {
        Author author = authorService.read(new Author().setId(id));
        return author;
    }

    @PutMapping(value = "/author/{id}")
    public void updateAuthor(@PathVariable("id") Long id,
                             @RequestBody Author author) {
        authorService.update(author.setId(id));
    }

    @DeleteMapping(value = "/author/{id}")
    public void deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(new Author().setId(id));
    }
}
