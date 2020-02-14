package com.epam.lab.controller;

import com.epam.lab.dto.AuthorDTO;
import com.epam.lab.service.AuthorService;
import com.epam.lab.validation.NewEntity;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/author")
    public AuthorDTO createAuthor(@Validated({NewEntity.class}) @RequestBody AuthorDTO author) {
        return authorService.create(author);
    }

    @GetMapping(value = "/author/{id}")
    public AuthorDTO readAuthor(@PathVariable("id") Long id) {
        return authorService.read(new AuthorDTO().setId(id));
    }

    @PutMapping(value = "/author/{id}")
    public void updateAuthor(@PathVariable("id") Long id, @Valid @RequestBody AuthorDTO author) {
        authorService.update(author.setId(id));
    }

    @DeleteMapping(value = "/author/{id}")
    public void deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(new AuthorDTO().setId(id));
    }
}
