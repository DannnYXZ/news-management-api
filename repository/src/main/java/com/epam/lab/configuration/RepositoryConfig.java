package com.epam.lab.configuration;

import com.epam.lab.dto.Author;
import com.epam.lab.dto.News;
import com.epam.lab.dto.Tag;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.repository.impl.AuthorRepositoryImpl;
import com.epam.lab.repository.impl.NewsRepositoryImpl;
import com.epam.lab.repository.impl.TagRepositoryImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class RepositoryConfig {

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getHikariDS());
        return jdbcTemplate;
    }

    @Bean
    public HikariDataSource getHikariDS() {
        HikariConfig config = new HikariConfig("/datasource.properties");
        HikariDataSource hikariDS = new HikariDataSource(config);
        return hikariDS;
    }

    @Bean
    NewsRepository getNewsRepository() {
        return new NewsRepositoryImpl(getJdbcTemplate());
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
