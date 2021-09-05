package com.veille2021.reactiveprogbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
public class Comment {

    @Id
    @Column
    private Integer idComment;

    @Column
    private String comment;

    @OneToOne
    private Customer customer;

    @OneToOne
    private Item item;

}
