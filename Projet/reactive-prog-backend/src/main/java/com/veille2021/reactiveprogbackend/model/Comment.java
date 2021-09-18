package com.veille2021.reactiveprogbackend.model;

import lombok.Data;
import lombok.With;
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
    @With
    //@Column(name = "id_customer")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @With
    //@Column(name = "id_item")
    private Item item;

    public Comment() {
    }

    public Comment(Integer idComment, String comment, Customer customer, Item item) {
        this.idComment = idComment;
        this.comment = comment;
        this.customer = customer;
        this.item = item;
    }


}
