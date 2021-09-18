package com.veille2021.reactiveprogbackend.handler;

import com.veille2021.reactiveprogbackend.model.Comment;
import com.veille2021.reactiveprogbackend.model.Customer;
import com.veille2021.reactiveprogbackend.model.Item;
import com.veille2021.reactiveprogbackend.repository.CommentRepository;
import com.veille2021.reactiveprogbackend.repository.CustomerRepository;
import com.veille2021.reactiveprogbackend.repository.ItemRepository;
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

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Mono<ServerResponse> getComments(ServerRequest serverRequest) {
        System.out.println(serverRequest);
        Optional<String> idItem = serverRequest.queryParam("idItem");
        return idItem.map(
                    s -> ok().contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(findCommentFromItem(Integer.valueOf(s)), Comment.class))
                .orElseGet(
                    () -> ok().contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(Mono.empty(), Comment.class));
    }

    public Flux<Comment> findCommentFromItem(Integer idItem){
        return repository.findAllByItem_IdItem(idItem)
                .flatMap(comment ->
                        Mono.just(comment)
                                .zipWith(customerRepository.findCustomerByIdComment(comment.getIdComment()))
                                .map(tupla -> tupla.getT1().withCustomer(tupla.getT2()))
                                .zipWith(itemRepository.findById(idItem))
                                .map(tupla -> tupla.getT1().withItem(tupla.getT2()))
                );
    }
}
