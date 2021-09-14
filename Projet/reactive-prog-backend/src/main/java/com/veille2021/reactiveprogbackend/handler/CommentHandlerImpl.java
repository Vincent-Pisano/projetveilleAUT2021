package com.veille2021.reactiveprogbackend.handler;

import com.veille2021.reactiveprogbackend.model.Comment;
import com.veille2021.reactiveprogbackend.model.Customer;
import com.veille2021.reactiveprogbackend.model.Item;
import com.veille2021.reactiveprogbackend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Optional;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static reactor.core.publisher.Mono.just;

@Service
public class CommentHandlerImpl implements CommentHandler{

    @Autowired
    private CommentRepository repository;

    @Override
    public Mono<ServerResponse> getComments(ServerRequest serverRequest) {
        System.out.println(serverRequest);
        Optional<String> idItem = serverRequest.queryParam("idItem");
        return idItem.map(
                    s -> ok().contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(repository.findAllByItem_IdItem(Integer.valueOf(s)), Comment.class))
                .orElseGet(
                    () -> ok().contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(Mono.empty(), Comment.class));
    }
}
