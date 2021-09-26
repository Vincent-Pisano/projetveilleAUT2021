package com.veille2021.reactiveprogbackend.controller;

import com.veille2021.reactiveprogbackend.model.Customer;
import com.veille2021.reactiveprogbackend.model.Order;
import com.veille2021.reactiveprogbackend.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
//@CrossOrigin("http://localhost:7500")
public class Controller {

    @Autowired
    private Service service;

    //@GetMapping("/{username}/{password}")
    //public Mono<Customer> login(@PathVariable String username, @PathVariable String password) {
    //    return service.login(username, password);
    //}

    @PostMapping("/subscribe")
    public Mono<Customer> createAccount(@RequestBody Customer customer) {
        return service.subscribe(customer);
    }

    @PostMapping(value = "/createOrder"
            //consumes = MediaType.APPLICATION_JSON_VALUE,
            //produces = MediaType.APPLICATION_JSON_VALUE,
            )
    public Mono<Order> create(@RequestBody Order order) {
        System.err.println(order);
        return service.createOrder(order)
                .flatMap(o -> {
                    if (Order.OrderStatus.ERROR.equals(o.getStatus())) {
                        return Mono.error(
                                new RuntimeException("Order processing failed, please try again later. "
                                ));
                    } else {
                        return Mono.just(o);
                    }
                });
    }


}
