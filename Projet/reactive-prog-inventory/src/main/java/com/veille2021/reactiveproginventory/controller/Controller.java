package com.veille2021.reactiveproginventory.controller;

import com.veille2021.reactiveproginventory.model.Order;
import com.veille2021.reactiveproginventory.service.InventoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Controller
public class Controller {

    private final InventoryService service;

    public Controller(InventoryService service) {
        this.service = service;
    }

    @PostMapping("/processOrder")
    public Mono<Order> processOrder(@RequestBody Order order) {
        return service.handleOrder(order);
    }
}
