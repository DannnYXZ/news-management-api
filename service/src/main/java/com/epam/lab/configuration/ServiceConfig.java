package com.epam.lab.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class ServiceConfig {

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
