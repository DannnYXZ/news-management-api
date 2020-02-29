package com.epam.lab.repository.impl;


import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.specification.EntitySpecification;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepositoryImpl implements TagRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Tag create(Tag tag) {
        entityManager.persist(tag);
        return tag;
    }

    @Override
    public void update(Tag tag) {
        Tag storedTag = entityManager.find(Tag.class, tag.getId());
        if (storedTag == null) {
            throw new EntityNotFoundException("No such tag.");
        }
        ObjectPatcher.patch(storedTag, tag);
    }

    @Override
    public void delete(Tag tag) {
        Tag storedTag = entityManager.find(Tag.class, tag.getId());
        if (storedTag == null) {
            throw new EntityNotFoundException("No such tag.");
        }
        entityManager.remove(storedTag);
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Tag> query(EntitySpecification<Tag> specification) {
        return entityManager.createQuery(specification.specified(entityManager)).getResultList();
    }
}
