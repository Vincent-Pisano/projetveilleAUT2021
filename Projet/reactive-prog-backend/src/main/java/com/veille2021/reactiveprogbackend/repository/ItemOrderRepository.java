package com.veille2021.reactiveprogbackend.repository;

import com.veille2021.reactiveprogbackend.model.ItemOrder;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemOrderRepository extends ReactiveCrudRepository<ItemOrder, Integer> {

}