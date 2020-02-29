package com.epam.lab.service.impl;

import com.epam.lab.dto.AuthorDTO;
import com.epam.lab.exception.EntityNotFoundException;
import com.epam.lab.model.Author;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.service.AuthorService;
import com.epam.lab.specification.impl.AuthorsByIdSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private EntityRepository<Author> authorRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AuthorServiceImpl(EntityRepository<Author> authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDTO create(AuthorDTO element) {
        Author author = modelMapper.map(element, Author.class);
        Author identifiedAuthor = authorRepository.create(author);
        return modelMapper.map(identifiedAuthor, AuthorDTO.class);
    }

    @Override
    public AuthorDTO read(AuthorDTO element) {
        List<Author> authors = authorRepository.query(new AuthorsByIdSpecification(element.getId()));
        if (!authors.isEmpty()) {
            return modelMapper.map(authors.get(0), AuthorDTO.class);
        } else {
            throw new EntityNotFoundException("No such author.");
        }
    }

    @Override
    public void update(AuthorDTO element) {
        authorRepository.update(modelMapper.map(element, Author.class));
    }

    @Override
    public void delete(AuthorDTO element) {
        authorRepository.delete(modelMapper.map(element, Author.class));
    }
}
