package com.epam.lab.repository.impl;


import com.epam.lab.exception.EntityExistsException;
import com.epam.lab.exception.EntityNotFoundException;
import com.epam.lab.exception.InsufficientEntityDataException;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.configuration.RepositoryTestConfig;
import com.epam.lab.specification.impl.NewsByIdSpecification;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.flywaydb.core.Flyway;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(classes = {RepositoryTestConfig.class}, loader = AnnotationConfigContextLoader.class)
public class NewsRepositoryImplTest {

    @ClassRule
    public static final SpringClassRule scr = new SpringClassRule();
    @Rule
    public final SpringMethodRule smr = new SpringMethodRule();
    @Autowired
    private Flyway flyway;
    @Autowired
    private NewsRepository newsRepository;

    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void restoreDatabase() {
        flyway.clean();
        flyway.migrate();
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
        Assert.assertEquals(actualNews.get(0), expected);
    }

    public Object[] parametersForTestReadNewsByIdEmptyList() {
        return new Object[]{
            new Object[]{-1L},
            new Object[]{100L}
        };
    }

    @Test
    @Parameters
    public void testReadNewsByIdEmptyList(long id) {
        List<News> actualNews = newsRepository.query(new NewsByIdSpecification(id));
        Assert.assertTrue(actualNews.isEmpty());
    }

    @Test(expected = EntityExistsException.class)
    public void testLinkExistingTagException() {
        newsRepository.linkTag(new News().setId(7), new Tag().setId(11));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testLinkNonexistentTagException() {
        newsRepository.linkTag(new News().setId(7), new Tag().setId(666));
    }

    @Test(expected = EntityExistsException.class)
    public void testLinkAuthorExistsException() {
        newsRepository.linkAuthor(new News().setId(10), new Author().setId(7));
    }

    @Test
    public void testCreateNews() {
        News identifiedNews = newsRepository.create(new News()
            .setAuthor(new Author().setName("Salami").setSurname("Greg"))
            .setTitle("Title")
            .setShortText("Hmmmm")
            .setFullText("Hmmmmmmmmmmmmmmmmmm")
            .setModificationDate(new Date())
            .setCreationDate(new Date()));
        Assert.assertTrue(identifiedNews.getId() != 0);
    }

    @Test(expected = InsufficientEntityDataException.class)
    public void testCreateNewsInsufficientDataException() {
        newsRepository.create(new News()
            .setAuthor(new Author().setName("Salami").setSurname("Greg"))
            .setModificationDate(new Date())
            .setCreationDate(new Date()));
    }

    @Test
    public void testUpdateNews() {
        try {
            newsRepository.update(new News()
                .setId(1)
                .setTitle("So lets update title."));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testDeleteNews() {
        try {
            newsRepository.delete(new News().setId(1));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteNonexistentNews() {
        newsRepository.delete(new News().setId(666));
    }
}