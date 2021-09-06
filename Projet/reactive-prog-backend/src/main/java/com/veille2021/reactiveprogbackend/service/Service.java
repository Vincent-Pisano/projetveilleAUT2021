package com.veille2021.reactiveprogbackend.service;

import com.veille2021.reactiveprogbackend.model.Customer;
import com.veille2021.reactiveprogbackend.repository.CommentRepository;
import com.veille2021.reactiveprogbackend.repository.ItemRepository;
import com.veille2021.reactiveprogbackend.repository.OrderRepository;
import com.veille2021.reactiveprogbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private CustomerRepository customerRepository;
    private CommentRepository commentRepository;
    private OrderRepository orderRepository;
    private ItemRepository itemRepository;

    /*public Mono<Customer> login(String username, String password)
    {
        Mono<Customer> response = Mono.empty();
        if (username != null && password != null)
            response = customerRepository.findByUsernameAndPassword(username, password);
        return response;
    }*/

    public Mono<Customer> subscribe(Customer customer)
    {
        if (customer != null && customer.usernameAndPasswordNotNull()) {
            return customerRepository.save(customer).onErrorReturn(new Customer());
        }
        return Mono.just(new Customer());
    }
}

/*
customerRepository.findByUsernameAndPassword(customer.getUsername(), customer.getPassword())
    .hasElement().subscribe(
        hasElements ->
        {
            System.out.println("DEBUG >> Mono has elements -> " + hasElements);
        }
    );

*/
