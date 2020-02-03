package com.epam.lab.service.impl;

import com.epam.lab.dto.Author;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.service.AuthorService;
import com.epam.lab.specification.impl.AuthorsByIdSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    @Autowired
    EntityRepository<Author> authorRepository;

    @Override
    public Author create(Author element) {
        Author identifiedAuthor = authorRepository.create(element);
        return identifiedAuthor;
    }

    @Override
    public Author read(Author element) {
        List<Author> authors = authorRepository.query(new AuthorsByIdSpecification(element.getId()));
        return authors.get(0);
    }

    @Override
    public void update(Author element) {
        authorRepository.update(element);
    }

    @Override
    public void delete(Author element) {
        authorRepository.delete(element);
    }
}
