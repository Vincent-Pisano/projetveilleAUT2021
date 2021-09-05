package com.veille2021.reactiveprogbackend.repository;

import com.veille2021.reactiveprogbackend.model.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemRepository extends ReactiveCrudRepository<Item, Integer> {

}