package com.veille2021.reactiveproginventory.configuration;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@org.springframework.context.annotation.Configuration
@EnableR2dbcRepositories
public class Configuration extends AbstractR2dbcConfiguration {

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(
                H2ConnectionConfiguration.builder()
                        //.inMemory("testdb")
                        .file("./inventoryDB")
                        .username("sa")
                        .password("password")
                        //.url("r2dbc:h2:file:///~/db/inventoryDB")
                        .build()
        );
    }
}
