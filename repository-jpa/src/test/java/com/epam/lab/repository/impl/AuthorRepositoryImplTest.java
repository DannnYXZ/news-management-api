package com.epam.lab.repository.impl;


import com.epam.lab.configuration.RepositoryTestConfig;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.specification.impl.AuthorsByIdSpecification;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.flywaydb.core.Flyway;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback(false)
@ContextConfiguration(classes = RepositoryTestConfig.class)
public class AuthorRepositoryImplTest {

    @Autowired
    AuthorRepository authorRepository;
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    Flyway flyway;

    @Before
    public void init() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void testSaveAuthor() {
        Author createdAuthor = authorRepository.create(new Author().setName("Author").setSurname("Surname"));
        Assert.assertTrue(createdAuthor.getId() != 0);
    }

    @Test
    public void testUpdateAuthor() {
        Author authorToUpdate = new Author().setId(5).setName("UPD");
        authorRepository.update(authorToUpdate);
        Assert.assertEquals(entityManager.find(Author.class, 5L).getName(), "UPD");
    }

    @Test
    public void testDeleteAuthor() {
        Author authorToDelete = new Author().setId(4);
        authorRepository.delete(authorToDelete);
        Assert.assertNull(entityManager.find(Author.class, 4L));
    }

    @Test
    public void testAuthorByIdQuery() {
        long expectedId = 3;
        List<Author> actualAuthors = authorRepository.query(new AuthorsByIdSpecification(expectedId));
        Assert.assertEquals(actualAuthors.get(0).getId(), expectedId);
    }
}
