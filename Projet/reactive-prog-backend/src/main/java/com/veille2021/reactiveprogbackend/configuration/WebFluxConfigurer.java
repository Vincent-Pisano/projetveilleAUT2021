package com.veille2021.reactiveprogbackend.configuration;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurerComposite;

@Configuration
@EnableWebFlux
class WebConfig implements WebFluxConfigurer {

        @Bean
        public WebFluxConfigurer corsConfigurer() {
                return new WebFluxConfigurerComposite() {

                        @Override
                        public void addCorsMappings(CorsRegistry registry) {
                                registry.addMapping("/**")
                                        .allowedOrigins("*")
                                        .allowedMethods("*")
                                        .allowedHeaders("Content-Type");
                        }
                };
        }

        /*private final ObjectMapper objectMapper;

        @Autowired
        public AppConfig(ObjectMapper objectMapper) {
                this.objectMapper = objectMapper;
                this.webClientBuilder = WebClient.builder()
                        .exchangeStrategies(exchangeStrategies());
        }

        private ExchangeStrategies exchangeStrategies() {
                Jackson2JsonEncoder encoder = new Jackson2JsonEncoder(objectMapper);
                Jackson2JsonDecoder decoder = new Jackson2JsonDecoder(objectMapper);
                return ExchangeStrategies
                        .builder()
                        .codecs(configurer -> {
                                configurer.defaultCodecs().jackson2JsonEncoder(encoder);
                                configurer.defaultCodecs().jackson2JsonDecoder(decoder);
                        }).build();
        }*/
}
