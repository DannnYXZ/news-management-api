package com.epam.lab.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@PropertySource("classpath:database.properties")
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
        //config.setJdbcUrl(environment.getProperty("url"));
        config.setUsername(environment.getProperty("name"));
        config.setPassword(environment.getProperty("password"));
        return config;
    }

}
