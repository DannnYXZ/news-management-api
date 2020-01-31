package com.epam.lab.configuration;

import com.epam.lab.dto.News;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.repository.impl.NewsRepositoryImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@PropertySource("classpath:datasource.properties")
public class RepositoryConfig {

    @Autowired
    private Environment environment;

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getHikariDS());
        return jdbcTemplate;
    }

    @Bean
    public HikariDataSource getHikariDS() {
        HikariDataSource hikariDS = new HikariDataSource(getHikariConfig());
        return hikariDS;
    }

    @Bean
    public HikariConfig getHikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(environment.getProperty("url"));
        config.setUsername(environment.getProperty("name"));
        config.setPassword(environment.getProperty("password"));
        return config;
    }

    @Bean
    EntityRepository<News> getNewsRepository() {
        return new NewsRepositoryImpl(getJdbcTemplate());
    }

    @Bean
    News getNews(){
        return new News();
    }
}
