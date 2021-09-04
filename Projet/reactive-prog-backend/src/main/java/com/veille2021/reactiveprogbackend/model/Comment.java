package com.veille2021.reactiveprogbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Data
@Entity
public class Comment {

    @Id
    @Column
    private Integer idComment;

    @Column
    private String comment;

    @OneToOne
    private User user;

    @OneToOne
    private Item item;

}
