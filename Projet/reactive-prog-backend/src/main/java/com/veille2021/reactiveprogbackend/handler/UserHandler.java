package com.veille2021.reactiveprogbackend.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface UserHandler {

    Mono<ServerResponse> subscribe(ServerRequest serverRequest);

    Mono<ServerResponse> login(ServerRequest serverRequest);

}
