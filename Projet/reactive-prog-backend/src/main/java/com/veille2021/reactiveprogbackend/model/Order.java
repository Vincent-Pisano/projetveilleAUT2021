package com.veille2021.reactiveprogbackend.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.data.annotation.Id;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "public.order")
public class Order {

    public enum OrderStatus {
        STDBY, PROCESSING, ERROR, COMPLETE
    }

    @Id
    @Column
    private Integer idOrder;

    @Column
    private BigDecimal totalPrice;

    @Column
    private OrderStatus status;

    //@OneToMany
    //@Column
    //@JsonSerialize(as = ArrayList.class)
    //@JsonDeserialize(as = ArrayList.class)
    //private List<Item> items;
    //private Item[] items;

    @OneToOne
    @Column
    private Customer customer;

    public Order setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }
}
