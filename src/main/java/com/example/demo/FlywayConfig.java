package com.example.demo;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
            .dataSource(dataSource())
            .load();

        flyway.migrate();

        return flyway;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/javagympass");
        dataSource.setUsername("docker");
        dataSource.setPassword("docker");
        return dataSource;
    }
}
