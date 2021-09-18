package com.veille2021.reactiveprogbackend.repository;

import com.veille2021.reactiveprogbackend.model.Customer;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
    Mono<Customer> findByUsernameAndPassword(String username, String password);

    @Query("SELECT customer.* FROM COMMENT comment\n" +
            "INNER JOIN CUSTOMER customer ON comment.id_customer = customer.id_customer\n" +
            " WHERE comment.id_comment = $1")
    public Mono<Customer> findCustomerByIdComment(Integer idComment);
}
