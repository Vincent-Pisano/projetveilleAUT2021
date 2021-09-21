package com.veille2021.reactiveproginventory;

import com.veille2021.reactiveproginventory.model.Item;
import com.veille2021.reactiveproginventory.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class ReactiveProgInventoryApplication implements CommandLineRunner {

    @Autowired
    private ItemRepository itemRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReactiveProgInventoryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Item item1 = new Item();
        item1.setName("Test1");
        item1.setPrice(new BigDecimal("0.0"));
        item1.setType("TEST1");

        Item item2 = new Item();
        item2.setName("Test2");
        item2.setPrice(new BigDecimal("0.0"));
        item2.setType("TEST2");

        itemRepository.save(item1);
        itemRepository.save(item2);

        itemRepository.findAll().subscribe(System.out::println);
    }
}
