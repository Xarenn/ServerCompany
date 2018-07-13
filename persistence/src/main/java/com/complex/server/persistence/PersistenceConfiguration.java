package com.complex.server.persistence;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.complex.server.persistence.domain",
    "com.complex.server.persistence.repositories"})
@EntityScan
@PropertySource(value = "classpath:application.properties")
@EnableAutoConfiguration
public class PersistenceConfiguration {

}
