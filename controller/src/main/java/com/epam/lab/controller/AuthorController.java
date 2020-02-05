package com.epam.lab.controller;

import com.epam.lab.dto.AuthorDTO;
import com.epam.lab.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/author")
    public AuthorDTO createAuthor(@RequestBody AuthorDTO author) {
        AuthorDTO identifiedAuthorDTO = authorService.create(author);
        return identifiedAuthorDTO;
    }

    @GetMapping(value = "/author/{id}")
    public AuthorDTO readAuthor(@PathVariable("id") Long id) {
        AuthorDTO author = authorService.read(new AuthorDTO().setId(id));
        return author;
    }

    @PutMapping(value = "/author/{id}")
    public void updateAuthor(@PathVariable("id") Long id,
                             @RequestBody AuthorDTO author) {
        authorService.update(author.setId(id));
    }

    @DeleteMapping(value = "/author/{id}")
    public void deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(new AuthorDTO().setId(id));
    }
}
