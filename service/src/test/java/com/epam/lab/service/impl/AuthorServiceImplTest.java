package com.epam.lab.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.lab.configuration.ServiceTestConfiguration;
import com.epam.lab.dto.AuthorDTO;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.service.AuthorService;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceTestConfiguration.class})
public class AuthorServiceImplTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorService authorService;

    @Before
    public void init() {
        reset(authorRepository);
    }

    @Test
    public void testCreateAuthor() {
        long expectedId = 777;
        when(authorRepository.create(any())).thenReturn(new Author().setId(expectedId));
        AuthorDTO inAuthor = new AuthorDTO().setName("Salami");
        AuthorDTO actualAuthor = authorService.create(inAuthor);
        Assert.assertEquals(actualAuthor.getId(), expectedId);
    }

    @Test
    public void testReadAuthor() {
        long id = 8976;
        when(authorRepository.query(any())).thenReturn(Arrays.asList(new Author().setId(id).setName("Hello Author")));
        AuthorDTO expectedAuthor = new AuthorDTO().setId(id).setName("Hello Author");
        AuthorDTO inAuthor = new AuthorDTO().setId(id);
        AuthorDTO actualAuthor = authorService.read(inAuthor);
        Assert.assertEquals(actualAuthor, expectedAuthor);
    }

    @Test
    public void testUpdateAuthor() {
        authorService.update(new AuthorDTO().setId(101));
        verify(authorRepository, times(1)).update(new Author().setId(101));
    }

    @Test
    public void testDeleteAuthor() {
        authorService.delete(new AuthorDTO().setId(102));
        verify(authorRepository, times(1)).delete(new Author().setId(102));
    }
}
