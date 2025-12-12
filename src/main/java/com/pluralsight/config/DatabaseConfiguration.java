package com.pluralsight.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource dataSource(
            @Value("${datasource.maxTotal:20}") int maxTotal,
            @Value("${datasource.maxIdle:10}") int maxIdle,
            @Value("${datasource.minIdle:2}") int minIdle,
            @Value("${datasource.maxWaitMillis:10000}") long maxWaitMillis
    ) {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setMaxTotal(maxTotal);
        ds.setMaxIdle(maxIdle);
        ds.setMinIdle(minIdle);
        ds.setMaxWaitMillis(maxWaitMillis);
        return ds;
    }
}
