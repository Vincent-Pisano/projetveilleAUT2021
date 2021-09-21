package com.veille2021.reactiveproginventory.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "item_order")
public class ItemOrder {

    @Id
    @Column
    private Integer idItemOrder;

    @ManyToOne
    private Item item;

    @Column
    private Integer quantity;
}
