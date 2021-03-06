package com.veille2021.reactiveproginventory.repository;

import com.veille2021.reactiveproginventory.model.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderRepository extends ReactiveCrudRepository<Order, Integer> {

}
