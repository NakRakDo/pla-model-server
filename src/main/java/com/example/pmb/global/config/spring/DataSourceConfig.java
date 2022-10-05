package com.example.pmb.global.config.spring;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* application.yaml을 오버라이드하여 작동함. */
@Configuration
public class DataSourceConfig {
    /**
     * application.yaml hikari config
     * @return
     */
    @Bean(name = "hikariConfig")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig(){
        return new HikariConfig();
    };

    /**
     * datasource based on hikari
     */
    @Bean(name = "dataSource")
    public HikariDataSource dataSource(@Qualifier("hikariConfig") HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    };
}
