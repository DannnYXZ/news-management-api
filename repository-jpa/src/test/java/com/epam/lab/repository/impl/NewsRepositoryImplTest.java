package com.epam.lab.repository.impl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.epam.lab.configuration.RepositoryTestConfig;
import com.epam.lab.exception.EntityExistsException;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.SearchCriteria;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.specification.impl.NewsByIdSpecification;
import com.epam.lab.specification.impl.NewsBySearchCriteriaSpecification;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.flywaydb.core.Flyway;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes = RepositoryTestConfig.class)
@RunWith(JUnitParamsRunner.class)
@Transactional
@Rollback(false)
public class NewsRepositoryImplTest {

    @ClassRule
    public static final SpringClassRule scr = new SpringClassRule();
    @Rule
    public final SpringMethodRule smr = new SpringMethodRule();
    @Autowired
    Flyway flyway;
    @Autowired
    NewsRepository newsRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Before
    public void init() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void testSaveNews() {
        News identifiedNews = newsRepository.create(new News()
            .setAuthor(new Author().setName("Salami").setSurname("Greg"))
            .setTitle("Title")
            .setShortText("Hmmmm")
            .setFullText("Hmmmmmmmmmmmmmmmmmm")
            .setModificationDate(new Date())
            .setCreationDate(new Date()));
        assertTrue(identifiedNews.getId() != 0);
    }

    @Test
    public void testUpdateNews() {
        String expectedTitle = "So lets update title.";
        newsRepository.update(new News()
            .setId(1)
            .setTitle(expectedTitle));
        assertEquals(entityManager.find(News.class, 1L).getTitle(), expectedTitle);
    }

    @Test
    public void testDeleteNews() {
        long id = 2;
        newsRepository.delete(new News().setId(id));
        Assert.assertNull(entityManager.find(News.class, id));
    }

    @Test
    public void testNewsCount() {
        long expectedCount = 17;
        long actualCount = newsRepository.count();
        Assert.assertEquals(expectedCount, actualCount);
    }

    public Object[] parametersForTestReadNewsById() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return new Object[]{
            new Object[]{17L, new News().setId(17)
                .setTitle(
                    "Larry King says 26-year age gap, religion took 'its toll' on marriage, ultimately led to divorce")
                .setShortText(
                    "Larry King is speaking out about the downfall of his marriage to Shawn Southwick, saying religion and their 26-year-year age gap eventually took \"its toll\" on their relationship.")
                .setFullText(
                    "“I got married a lot,” he said. “But in my head, I’m not a marrying guy. When I grew up, nobody lived together. If you fell in love, you got married. And so I married the ones that I loved. But what I loved at 20 is not what I loved at 30 and what I loved at 30 is not what I loved at 40.”")
                .setCreationDate(dateFormat.parse("2018-02-12 21:00"))
                .setModificationDate(dateFormat.parse("2018-02-12 21:00"))
                .setAuthor(new Author().setId(1).setName("Ziad").setSurname("Smith"))
                .setTags(new ArrayList<>(
                Arrays.asList(
                    new Tag().setId(1).setName("Travelling"),
                    new Tag().setId(10).setName("Food"),
                    new Tag().setId(11).setName("UFO"))))},
            new Object[]{4L, new News().setId(4)
                .setTitle("'Fox & Friends' on Nancy Pelosi's attempt to 'mind meld with AOC' at SOTU")
                .setShortText("\"Fox & Friends\" hosts on Nancy Pelosi's behavior at the State of the Union address.")
                .setFullText("video")
                .setCreationDate(dateFormat.parse("2003-07-02 21:00"))
                .setModificationDate(dateFormat.parse("2003-07-02 21:00"))
                .setAuthor(new Author().setId(14).setName("Kate").setSurname("Lopez"))
                .setTags(new ArrayList<>(
                Arrays.asList(
                    new Tag().setId(11).setName("UFO"),
                    new Tag().setId(14).setName("Health"),
                    new Tag().setId(18).setName("Extreme"))))}
        };
    }

    @Test
    @Parameters
    public void testReadNewsById(long id, News expected) {
        List<News> actualNews = newsRepository.query(new NewsByIdSpecification(id));
        News actual = actualNews.get(0); // FIXME: PersistenceBag hashCode problem
        Assert.assertEquals(actual, expected);
    }

    @Test(expected = EntityExistsException.class)
    public void testLinkAuthorException() {
        Author authorToLink = new Author().setId(3);
        News targetNews = new News().setId(1);
        newsRepository.linkAuthor(targetNews, authorToLink);
    }

    @Test
    public void testUnlinkAuthor() {
        Author authorToUnlink = new Author().setId(2);
        News targetNews = new News().setId(16);
        newsRepository.unlinkAuthor(targetNews, authorToUnlink);
    }

    @Test
    public void testLinkTag() {
        Tag tagToLink = new Tag().setId(3);
        News targetNews = new News().setId(2);
        newsRepository.linkTag(targetNews, tagToLink);
    }

    @Test
    public void testUnlinkTag() {
        Tag tagToUnlink = new Tag().setId(3);
        News targetNews = new News().setId(2);
        newsRepository.unlinkTag(targetNews, tagToUnlink);
    }

    @Test
    public void testGetNewsByAuthor() {
        SearchCriteria criteria = new SearchCriteria().setAuthor(
            new Author().setName("Nick")
        );
        List<News> actualNews = newsRepository.query(new NewsBySearchCriteriaSpecification(criteria));
        for (News news : actualNews) {
            assertEquals(news.getAuthor().getName(), "Nick");
        }
    }

    @Test
    public void testGetNewsByTags() {
        List<Tag> expectedTags = Arrays.asList(
            new Tag().setName("Critical"),
            new Tag().setName("Dogs")
        );
        SearchCriteria criteria = new SearchCriteria().setTags(expectedTags);
        List<News> actualNews = newsRepository.query(new NewsBySearchCriteriaSpecification(criteria));
        for (News news : actualNews) {
            int count = 0;
            for (Tag tag : news.getTags()) {
                if (expectedTags.contains(new Tag().setName(tag.getName()))) {
                    count++;
                }
            }
            Assert.assertEquals(count, expectedTags.size());
        }
    }
}
