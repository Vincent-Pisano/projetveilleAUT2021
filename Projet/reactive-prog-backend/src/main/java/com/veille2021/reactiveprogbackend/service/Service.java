package com.veille2021.reactiveprogbackend.service;

import com.veille2021.reactiveprogbackend.model.Customer;
import com.veille2021.reactiveprogbackend.model.Order;
import com.veille2021.reactiveprogbackend.repository.CommentRepository;
import com.veille2021.reactiveprogbackend.repository.ItemRepository;
import com.veille2021.reactiveprogbackend.repository.OrderRepository;
import com.veille2021.reactiveprogbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private CustomerRepository customerRepository;
    private CommentRepository commentRepository;
    @Autowired
    private OrderRepository orderRepository;
    private ItemRepository itemRepository;

    WebClient client = WebClient.create();

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

    public Mono<Order> createOrder(Order order) {
        System.out.println("test : " + order);
        order.setItems(new ArrayList<>());
        //order.setItems(new Item[0]);
        order.setCustomer(null);
        System.out.println("test : " + order);
        return Mono.just(order)
                .flatMap(orderRepository::save)
                .flatMap(o -> {
                    System.out.println("test : " + o);
                    return client.method(HttpMethod.POST)
                            .uri("https://localhost:8181/processOrder")
                            .body(BodyInserters.fromValue(o))
                            .retrieve().bodyToMono(Order.class);
                });
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
