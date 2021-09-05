package com.veille2021.reactiveprogbackend.router;

import com.veille2021.reactiveprogbackend.handler.UserHandler;
import com.veille2021.reactiveprogbackend.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class RouterConfig {

    private final UserHandler userHandler;

    public RouterConfig(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @Bean
    @CrossOrigin("http://localhost:7500")
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions
                .route(
                        RequestPredicates.POST("/subscribe"),
                        userHandler::subscribe)
                .andRoute(RequestPredicates.GET("/login"),
                        userHandler::login);

    }

}
