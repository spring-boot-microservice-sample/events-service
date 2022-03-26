package com.example.br.events.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@Configuration
public class TestConfiguration {

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean getRepositoryPopulator() {

        final Jackson2RepositoryPopulatorFactoryBean dataFactory= new Jackson2RepositoryPopulatorFactoryBean();
        dataFactory.setResources(new Resource[]{ new ClassPathResource("test-events.json")});

        return dataFactory;
    }
}
