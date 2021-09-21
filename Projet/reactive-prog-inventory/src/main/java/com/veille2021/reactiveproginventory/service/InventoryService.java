package com.veille2021.reactiveproginventory.service;

import com.veille2021.reactiveproginventory.model.Order;
import com.veille2021.reactiveproginventory.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InventoryService {

    @Autowired
    private ItemRepository itemRepository;

    private final Logger logger;

    public InventoryService() {
        this.logger = LoggerFactory.getLogger(InventoryService.class);
    }

    @Transactional
    public Mono<Order> handleOrder(Order order) {
        return Flux.fromIterable(order.getItemOrders())
                .flatMap(i -> itemRepository.findById(i.getItem().getIdItem()))
                .flatMap(p -> {
                    int q = order.getItemOrders().stream()
                            .filter(l -> l.getItem().getIdItem().equals(p.getIdItem()))
                            .findAny().get()
                            .getQuantity();
                    if (p.getQuantity() >= q) {
                        p.setQuantity(p.getQuantity() - q);
                        return itemRepository.save(p);
                    } else {
                        return Mono.error(new RuntimeException("Product is out of stock: " + p.getName()));
                    }
                })
                .then(Mono.just(order.setStatus(Order.OrderStatus.COMPLETE)));
    }
}
