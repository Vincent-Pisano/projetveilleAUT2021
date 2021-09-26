package com.veille2021.reactiveprogbackend.repository;

import com.veille2021.reactiveprogbackend.model.Order;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface OrderRepository extends ReactiveCrudRepository<Order, Integer> {

    @Override
    //@Query(value = "INSERT INTO public.order VALUES ($1.idOrder, $1.totalPrice, $1.status, $1.customer.id_customer)")
    <S extends Order> Mono<S> save(S s);
}
