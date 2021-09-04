package com.veille2021.reactiveprogbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;

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
    private User user;


}
