package com.veille2021.reactiveprogbackend.configuration;

/*import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories
public class PostgresConfig extends AbstractR2dbcConfiguration {

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host("localhost")
                        .port(5432)
                        .username("postgres")
                        .password("mysecretpassword")
                        .database("myDatabase")
                        .build());
    }
}*/

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.dialect.PostgresDialect;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.core.DefaultReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;

@Configuration
@EnableR2dbcRepositories("com.veille2021.reactiveprogbackend.repository")
public class PostgresConfig extends AbstractR2dbcConfiguration {
    @Bean
    @Override
    public PostgresqlConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration
                .builder()
                .host("localhost")
                .database("postgres")
                .username("postgres")
                .port(5432)
                .build());
    }

    @Bean
    public DatabaseClient r2dbcDatabaseClient(@Qualifier("connectionFactory") ConnectionFactory connectionFactory){
        return DatabaseClient.builder().connectionFactory(connectionFactory).build();
    }

    /*@Bean
    R2dbcRepositoryFactory repositoryFactory(@Qualifier("databaseClient") DatabaseClient client){
        RelationalMappingContext context = new RelationalMappingContext();
        context.afterPropertiesSet();
        return new R2dbcRepositoryFactory(client,context, new DefaultReactiveDataAccessStrategy(new PostgresDialect()));
    }*/

}
