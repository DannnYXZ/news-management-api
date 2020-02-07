package com.epam.lab.repository.configuration;

import com.epam.lab.model.Author;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.repository.impl.AuthorRepositoryImpl;
import com.epam.lab.repository.impl.NewsRepositoryImpl;
import com.epam.lab.repository.impl.TagRepositoryImpl;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class RepositoryTestConfig {
    @Bean
    DataSource getDataSource() {
        try {
            EmbeddedPostgres pg = EmbeddedPostgres.start();
            return pg.getPostgresDatabase();
        } catch (IOException e) {
            throw new RuntimeException("Failed to instantiate embedded pg.");
        }
    }

    @Bean
    JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    NewsRepository getNewsRepository() {
        return new NewsRepositoryImpl(getJdbcTemplate());
    }

    @Bean
    Flyway getFlyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(getDataSource());
        flyway.setLocations("db/migration");
        return flyway;
    }

    @Bean
    EntityRepository<Tag> getTagRepository() {
        return new TagRepositoryImpl(getJdbcTemplate());
    }

    @Bean
    EntityRepository<Author> getAuthorRepository() {
        return new AuthorRepositoryImpl(getJdbcTemplate());
    }
}
