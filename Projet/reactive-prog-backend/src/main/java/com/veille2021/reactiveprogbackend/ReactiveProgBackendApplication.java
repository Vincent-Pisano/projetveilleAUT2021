package com.veille2021.reactiveprogbackend;

import com.veille2021.reactiveprogbackend.converter.CommentConverter;
import com.veille2021.reactiveprogbackend.model.*;
import com.veille2021.reactiveprogbackend.repository.CommentRepository;
import com.veille2021.reactiveprogbackend.repository.CustomerRepository;
import com.veille2021.reactiveprogbackend.repository.ItemRepository;
import com.veille2021.reactiveprogbackend.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class ReactiveProgBackendApplication implements CommandLineRunner {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Service service;

    public static void main(String[] args) {
        SpringApplication.run(ReactiveProgBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //CommentConverter converter = new CommentConverter();
        //Integer idItem = 95;

        //commentRepository.findAllByItem_IdItem(idItem).subscribe(System.out::println);

        //findCommentFromItem(idItem).subscribe(System.out::println);


        //Flux<Integer> childCompanyIds = commentRepository.findCustomerByItem_IdItem(95);
        //childCompanyIds.subscribe(System.out::println);

        /*commentRepository.findAllCustomerByItem_IdItem(idItem)
                .flatMap(idCustomer ->
                        customerRepository.findById(idCustomer)
                )
                .flatMap(customer ->
                        commentRepository.findCommentByCustomer_IdCustomerAndItem_IdItem(
                                customer.getIdCustomer(), idItem)
                        )
                .subscribe(System.out::println);*/
        Item item1 = new Item();
        item1.setName("Test1");
        item1.setPrice(new BigDecimal("2.35"));
        item1.setType("TEST");
        item1.setQuantity(0);
        itemRepository.save(item1).then(service.createOrder(getOrder()));


    }

    public Flux<Comment> findCommentFromItem(Integer idItem){
        return commentRepository.findAllByItem_IdItem(idItem)
                .flatMap(comment ->
                        Mono.just(comment)
                                .zipWith(customerRepository.findCustomerByIdComment(comment.getIdComment()))
                                .map(tupla -> tupla.getT1().withCustomer(tupla.getT2()))
                                .zipWith(itemRepository.findById(idItem))
                                .map(tupla -> tupla.getT1().withItem(tupla.getT2()))
                );
    }

    public Order getOrder() {
        Item item1 = new Item();
        item1.setName("Test1");
        item1.setPrice(new BigDecimal("2.35"));
        item1.setType("TEST");
        item1.setQuantity(13);

        List<Item> items = new ArrayList<>();
        items.add(item1);

        List<ItemOrder> listItemOrder = new ArrayList<>();

        ItemOrder itemOrder1 = new ItemOrder();
        itemOrder1.setItem(item1);
        listItemOrder.add(itemOrder1);

        Customer customer = new Customer();
        customer.setIdCustomer(101);
        customer.setUsername("Username123");
        customer.setPassword("Pasword123");

        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(Order.OrderStatus.PROCESSING);
        order.setItemOrders(listItemOrder);
        order.setTotalPrice(new BigDecimal(0));
        System.out.println(order);
        return order;
    }
}
