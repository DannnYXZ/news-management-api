package com.epam.lab.repository.impl;


import com.epam.lab.configuration.RepositoryTestConfig;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.specification.impl.TagsByIdSpecification;
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

@ContextConfiguration(classes = RepositoryTestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback(false)
public class TagRepositoryImplTest {

    @Autowired
    TagRepository tagRepository;
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
    public void testSaveTag() {
        Tag createdTag = tagRepository.create(new Tag().setName("Tag"));
        Assert.assertTrue(createdTag.getId() != 0);
    }

    @Test
    public void testUpdateTag() {
        Tag tagToUpdate = new Tag().setId(5).setName("UPD");
        tagRepository.update(tagToUpdate);
        Assert.assertEquals(entityManager.find(Tag.class, 5L).getName(), "UPD");
    }

    @Test
    public void testDeleteTag() {
        Tag tagToDelete = new Tag().setId(4);
        tagRepository.delete(tagToDelete);
        Assert.assertNull(entityManager.find(Tag.class, 4L));
    }

    @Test
    public void testTagByIdQuery() {
        long expectedId = 3;
        List<Tag> actualTags = tagRepository.query(new TagsByIdSpecification(expectedId));
        Assert.assertEquals(actualTags.get(0).getId(), expectedId);
    }
}
