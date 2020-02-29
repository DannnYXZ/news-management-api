package com.epam.lab.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
