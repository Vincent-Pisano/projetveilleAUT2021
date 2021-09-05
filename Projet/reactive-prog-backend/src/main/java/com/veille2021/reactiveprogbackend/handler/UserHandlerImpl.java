package com.veille2021.reactiveprogbackend.handler;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static reactor.core.publisher.Mono.just;

@Service
public class UserHandlerImpl implements UserHandler{

    @Override
    public Mono<ServerResponse> subscribe(ServerRequest serverRequest) {
        System.out.println("test");
        return ok().body(just(100), Integer.class);
    }

}
