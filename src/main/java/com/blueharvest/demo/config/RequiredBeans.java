package com.blueharvest.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequiredBeans {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
