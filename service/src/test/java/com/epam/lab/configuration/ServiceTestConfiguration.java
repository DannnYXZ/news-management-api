package com.epam.lab.configuration;

import com.epam.lab.repository.NewsRepository;
import org.mockito.Mockito;
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
        return Mockito.mock(NewsRepository.class);
    }
}
