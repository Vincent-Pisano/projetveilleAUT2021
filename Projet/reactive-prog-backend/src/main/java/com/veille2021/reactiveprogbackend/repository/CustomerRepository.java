package com.veille2021.reactiveprogbackend.repository;

import com.veille2021.reactiveprogbackend.model.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
    Mono<Customer> findByUsernameAndPassword(String username, String password);
}
