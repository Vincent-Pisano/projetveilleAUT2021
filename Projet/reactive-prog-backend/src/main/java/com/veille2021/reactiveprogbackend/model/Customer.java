package com.veille2021.reactiveprogbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
public class Customer {

    @Id
    @Column
    private Integer idCustomer;

    @Column(unique=true)
    private String username;

    @Column
    private String password;

    public Customer(Integer idCustomer, String username, String password) {
        this.idCustomer = idCustomer;
        this.username = username;
        this.password = password;
    }
}
