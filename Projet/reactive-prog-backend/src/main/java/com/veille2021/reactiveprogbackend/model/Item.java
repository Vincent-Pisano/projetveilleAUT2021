package com.veille2021.reactiveprogbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column
    private Integer idItem;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private BigDecimal price;

    @Column
    private Integer quantity;

}
