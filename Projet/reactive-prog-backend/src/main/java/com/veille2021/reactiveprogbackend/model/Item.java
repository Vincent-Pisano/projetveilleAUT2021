package com.veille2021.reactiveprogbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Table(name = "item")
public class Item implements Serializable {

    @Id
    @Column(name = "id_item")
    private Integer idItem;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private BigDecimal price;

}
