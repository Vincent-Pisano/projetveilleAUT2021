package com.veille2021.reactiveprogbackend.handler;

import com.veille2021.reactiveprogbackend.model.Customer;
import com.veille2021.reactiveprogbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static reactor.core.publisher.Mono.just;

@Service
public class UserHandlerImpl implements UserHandler{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Mono<ServerResponse> subscribe(ServerRequest serverRequest) {
        //System.out.println(serverRequest.attribute("Customer"));
        //System.out.println(serverRequest);
        //System.out.println(serverRequest.bodyToMono(Object.class)
        //        .subscribe(System.out::println));
        //System.out.println(serverRequest.attributes());
        //serverRequest.bodyToMono(String.class).doOnNext(body -> System.out.println(body));
        //serverRequest.bodyToMono(String.class).hasElement().subscribe(System.out::println);
        Mono<String> body = serverRequest.bodyToMono(String.class);
        body.subscribe(System.out::println);
        return ok().body(just(100), Integer.class);
    }

    @Override
    public Mono<ServerResponse> login(ServerRequest serverRequest) {
        Optional<String> username = serverRequest.queryParam("username");
        Optional<String> password = serverRequest.queryParam("password");
        if (username.isPresent() && password.isPresent())
            return ok().body(customerRepository.findByUsernameAndPassword(username.get(), password.get()), Customer.class);
        return ok().body(Mono.empty(), Customer.class); //à vérifier
    }

}
