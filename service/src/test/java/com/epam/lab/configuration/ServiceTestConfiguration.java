package com.epam.lab.configuration;

import static org.mockito.Mockito.mock;

import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.repository.TagRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "com.epam.lab")
public class ServiceTestConfiguration {

    @Bean
    @Primary
    public NewsRepository getNewsRepositoryMock() {
        return mock(NewsRepository.class);
    }

    @Bean
    @Primary
    public TagRepository getTagRepositoryMock() {
        return mock(TagRepository.class);
    }

    @Bean
    @Primary
    public AuthorRepository getAuthorRepositoryMock() {
        return mock(AuthorRepository.class);
    }
}
