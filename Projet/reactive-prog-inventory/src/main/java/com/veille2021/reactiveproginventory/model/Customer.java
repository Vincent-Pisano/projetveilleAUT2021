package com.veille2021.reactiveproginventory.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @Column(name = "id_customer")
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

    public Customer() { }

    public boolean usernameAndPasswordNotNull() {
        return username != null && password != null;
    }
}
