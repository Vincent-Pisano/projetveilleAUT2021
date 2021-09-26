package com.veille2021.reactiveproginventory.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
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

    @OneToMany
    @Column
    private List<Item> items;

    @OneToOne
    @Column
    private Customer customer;

    public Order setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }
}
