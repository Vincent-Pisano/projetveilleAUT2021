package com.veille2021.reactiveprogbackend.repository;

import com.veille2021.reactiveprogbackend.model.Order;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface OrderRepository extends ReactiveCrudRepository<Order, Integer> {

    //@Override
    //@Query(value = "INSERT INTO public.order (total_price, status) " +
    //        "VALUES ($1, $2)")
    //<S extends Order> Mono<S> save(S s);
}
