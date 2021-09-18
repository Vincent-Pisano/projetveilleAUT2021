package com.veille2021.reactiveprogbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "comment")
public class Comment implements Serializable {

    @Id
    @Column
    private Integer idComment;

    @Column
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    //@Column(name = "id_customer")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    //@Column(name = "id_item")
    private Item item;


}
