package com.veille2021.reactiveproginventory.repository;

import com.veille2021.reactiveproginventory.model.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ItemRepository extends ReactiveCrudRepository<Item, Integer> {

    Mono<Item> findItemByName(String name);

}