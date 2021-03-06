package com.veille2021.reactiveproginventory;

import com.veille2021.reactiveproginventory.model.Customer;
import com.veille2021.reactiveproginventory.model.Item;
import com.veille2021.reactiveproginventory.model.Order;
import com.veille2021.reactiveproginventory.repository.ItemRepository;
import com.veille2021.reactiveproginventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@SpringBootApplication
public class ReactiveProgInventoryApplication implements CommandLineRunner {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private InventoryService service;

    public static void main(String[] args) {
        SpringApplication.run(ReactiveProgInventoryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Item item1 = new Item();
        item1.setName("Test1");
        item1.setPrice(new BigDecimal("2.35"));
        item1.setType("TEST");
        item1.setQuantity(50);

        Item item2 = new Item();
        item2.setName("Test2");
        item2.setPrice(new BigDecimal("5.69"));
        item2.setType("TEST");
        item2.setQuantity(35);


        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        //itemRepository.deleteAll(items);
        Flux.fromIterable(items)
            .flatMap(item -> itemRepository.findItemByName(item.getName()))
            .doOnNext(item -> itemRepository.delete(item).subscribe())
            .collectList()
            .flatMap(itemList -> itemRepository.saveAll(items)
                .map(item -> {
                    item.setQuantity(new Random().nextInt(item.getQuantity()));
                    return item;
                }).collectList()
                .map(listItems -> {
                    Customer customer = new Customer();
                    customer.setIdCustomer(101);
                    customer.setUsername("Username123");
                    customer.setPassword("Pasword123");

                    Order order = new Order();
                    order.setIdOrder(1);
                    order.setCustomer(customer);
                    order.setStatus(Order.OrderStatus.PROCESSING);
                    order.setItems(listItems);
                    order.setTotalPrice(new BigDecimal(0));
                    return order;
                })
            )
            .subscribe(order -> {
                service.handleOrder(order).doOnNext(order1 -> {
                    Flux.fromIterable(order1.getItems())
                            .flatMap(item -> itemRepository.findById(item.getIdItem()));
                })
                .subscribe(System.out::println);
            });













        //order.setTotalPrice(itemOrders.forEach(itemOrder -> {
        //    itemOrder.getItem().getPrice().multiply(BigDecimal.valueOf(itemOrder.getQuantity();
        //}));
        //order.setTotalPrice()
        //        .add(itemOrder2.getItem().getPrice().multiply(BigDecimal.valueOf(itemOrder2.getQuantity())))));


        // ne fonctionne pas ?


        //ne fonctione pas
        //order.getItemOrders().forEach(itemOrder -> {
        //    itemRepository.findById(itemOrder.getItem().getIdItem()).subscribe(System.out::println);
        //});

        //ne fonctione pas
        //itemRepository.findById(1001).subscribe((System.out::println));

        //itemRepository.save(item1);
        //itemRepository.save(item2);

        //itemRepository.findAll().subscribe(System.out::println);
    }
}
