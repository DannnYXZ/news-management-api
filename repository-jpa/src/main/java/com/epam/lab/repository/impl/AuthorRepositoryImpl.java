package com.epam.lab.repository.impl;

import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.specification.EntitySpecification;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author create(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    public void update(Author author) {
        Author storedAuthor = entityManager.find(Author.class, author.getId());
        if (storedAuthor == null) {
            throw new EntityNotFoundException("No such author.");
        }
        ObjectPatcher.patch(storedAuthor, author);
    }

    @Override
    public void delete(Author author) {
        Author storedAuthor = entityManager.find(Author.class, author.getId());
        if (storedAuthor == null) {
            throw new EntityNotFoundException("No such author.");
        }
        entityManager.remove(storedAuthor);
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Author> query(EntitySpecification<Author> specification) {
        return entityManager.createQuery(specification.specified(entityManager)).getResultList();
    }
}
