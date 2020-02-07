package com.epam.lab.configuration;

import com.epam.lab.repository.NewsRepository;
import com.epam.lab.service.NewsService;
import com.epam.lab.service.impl.NewsServiceImpl;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceTestConfiguration {

    @Bean
    public NewsRepository getNewsRepositoryMock() {
        return Mockito.mock(NewsRepository.class);
    }

    @Bean
    public NewsService getNewsService() {
        return new NewsServiceImpl();
    }

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
