package com.epam.lab.configuration;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.epam.lab")
@EnableTransactionManagement
@PropertySource(value = {"classpath:application.properties"})
public class RepositoryTestConfig {

    @Autowired
    private Environment environment;

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

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan("com.epam.lab.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }
}
