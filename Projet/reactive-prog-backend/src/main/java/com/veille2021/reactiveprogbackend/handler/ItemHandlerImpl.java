package com.veille2021.reactiveprogbackend.handler;

import com.veille2021.reactiveprogbackend.model.Item;
import com.veille2021.reactiveprogbackend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static reactor.core.publisher.Mono.just;

@Service
public class ItemHandlerImpl implements ItemHandler{

    private static final Random random = new Random();

    @Autowired
    private ItemRepository repository;

    @Override
    public Mono<ServerResponse> getItems(ServerRequest serverRequest) {
        System.out.println(serverRequest);
        return ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(Flux.interval(Duration.ofMillis(random.nextInt(200))).zipWith(repository.findAll()), Item.class);
    }
}
