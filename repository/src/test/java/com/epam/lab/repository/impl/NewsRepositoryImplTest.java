package com.epam.lab.repository.impl;


import com.epam.lab.model.News;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.repository.configuration.RepositoryTestConfig;
import com.epam.lab.specification.impl.NewsByIdSpecification;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.flywaydb.core.Flyway;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(classes = RepositoryTestConfig.class)
public class NewsRepositoryImplTest {
    private static Flyway flyway;
    private static EmbeddedPostgres epg;
    private static NewsRepository newsRepository;

    @BeforeClass
    public static void setUp() throws Exception {
        epg = EmbeddedPostgres.start();
        flyway = new Flyway();
        flyway.setDataSource(epg.getPostgresDatabase());
        flyway.setLocations("db/migration");

        JdbcTemplate template = new JdbcTemplate(epg.getPostgresDatabase());
        newsRepository = new NewsRepositoryImpl(template);
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

    public Object[] parametersForTestReadNewsById() {
        return new Object[]{
                new Object[]{17L, new News()},
                new Object[]{17L, new News()}
        };
    }

    @Test
    @Parameters
    public void testReadNewsById(long id, News expected) {
        List<News> actualNews = newsRepository.query(new NewsByIdSpecification(id));
        Assert.assertEquals(actualNews.get(0), expected);
    }

    @AfterAll
    static void tearDown() throws IOException {
        epg.close();
    }
}