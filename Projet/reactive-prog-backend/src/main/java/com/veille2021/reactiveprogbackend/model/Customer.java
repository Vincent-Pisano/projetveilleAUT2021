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

    public boolean isUsernameAndPasswordNotNull() {
        return username != null && password != null;
    }
}
