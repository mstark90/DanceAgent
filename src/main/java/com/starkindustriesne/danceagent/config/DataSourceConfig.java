package com.starkindustriesne.danceagent.config;

import org.mariadb.jdbc.Driver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource(@Value("${datasource.url}") String url,
                                 @Value("${datasource.user}") String userName,
                                 @Value("${datasource.password}") String password) {
        return DataSourceBuilder.create()
                .driverClassName(Driver.class.getName())
                .url(url)
                .username(userName)
                .password(password)
                .build();
    }
}
