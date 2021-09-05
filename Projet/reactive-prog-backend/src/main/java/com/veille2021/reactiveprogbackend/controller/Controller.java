package com.veille2021.reactiveprogbackend.controller;

import com.veille2021.reactiveprogbackend.model.Customer;
import com.veille2021.reactiveprogbackend.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

//@RestController
//@CrossOrigin("http://localhost:7500")
public class Controller {

    /*@Autowired
    private Service service;

    @GetMapping("/{username}/{password}")
    public Mono<Customer> login(@PathVariable String username, @PathVariable String password) {
        return service.login(username, password);
    }

    @PostMapping("/subscribe")
    public Mono<Customer> createAccount(@RequestBody Customer customer) {
        return service.subscribe(customer);
    }*/

}
