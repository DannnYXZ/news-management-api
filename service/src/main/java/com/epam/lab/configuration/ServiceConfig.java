package com.epam.lab.configuration;

import com.epam.lab.service.AuthorService;
import com.epam.lab.service.NewsService;
import com.epam.lab.service.TagService;
import com.epam.lab.service.impl.AuthorServiceImpl;
import com.epam.lab.service.impl.NewsServiceImpl;
import com.epam.lab.service.impl.TagServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RepositoryConfig.class})
public class ServiceConfig {
    @Bean
    NewsService getNewsService() {
        return new NewsServiceImpl();
    }

    @Bean
    AuthorService getAuthorService() {
        return new AuthorServiceImpl();
    }

    @Bean
    TagService getTagService() {
        return new TagServiceImpl();
    }
}
