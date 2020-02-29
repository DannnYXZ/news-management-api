package com.epam.lab.repository.configuration;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@ComponentScan(basePackages = "com.epam.lab")
public class RepositoryTestConfig {

    @Bean
    @Primary
    DataSource getDataSource() {
        try {
            EmbeddedPostgres pg = EmbeddedPostgres.start();
            return pg.getPostgresDatabase();
        } catch (IOException e) {
            throw new RuntimeException("Failed to instantiate embedded pg.");
        }
    }

    @Bean
    @Primary
    JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    Flyway getFlyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(getDataSource());
        flyway.setLocations("db/migration");
        return flyway;
    }
}
