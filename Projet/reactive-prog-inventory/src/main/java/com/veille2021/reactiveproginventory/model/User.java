package com.veille2021.reactiveprogbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import javax.persistence.Entity;

@Data
@Entity
public class User {

    @Id
    @Column
    private Integer idUser;

    @Column
    private String username;

    @Column
    private String password;
}
