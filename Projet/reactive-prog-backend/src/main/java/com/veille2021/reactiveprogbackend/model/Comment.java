package com.veille2021.reactiveprogbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Data
@Table
public class Comment {

    @Id
    @Column
    private Integer idComment;

    @Column
    private String comment;

    @Column
    private Customer customer;

    @Column
    private Item item;

}
