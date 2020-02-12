package com.epam.lab.configuration;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan
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
}
