package com.veille2021.reactiveprogbackend.router;

import com.veille2021.reactiveprogbackend.handler.CommentHandler;
import com.veille2021.reactiveprogbackend.handler.ItemHandler;
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
    private final ItemHandler itemHandler;
    private final CommentHandler commentHandler;

    public RouterConfig(UserHandler userHandler, ItemHandler itemHandler, CommentHandler commentHandler) {
        this.userHandler = userHandler;
        this.itemHandler = itemHandler;
        this.commentHandler = commentHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions
                .route(
                        RequestPredicates.GET("/login"),
                        userHandler::login)
                //.andRoute(RequestPredicates.GET("/login"),
                //        userHandler::subscribe)
                .andRoute(RequestPredicates.GET("/getItems"),
                        itemHandler::getItems)
                .andRoute(RequestPredicates.GET("/getComments"),
                        commentHandler::getComments);
    }

}
