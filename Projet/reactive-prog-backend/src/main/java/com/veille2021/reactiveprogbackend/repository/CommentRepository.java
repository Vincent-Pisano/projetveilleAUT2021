package com.veille2021.reactiveprogbackend.repository;

import com.veille2021.reactiveprogbackend.model.Comment;
import com.veille2021.reactiveprogbackend.model.Customer;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CommentRepository extends ReactiveCrudRepository<Comment, Integer> {
    @Query("SELECT comment.id_comment, comment.comment, customer.*, item.* FROM COMMENT comment\n" +
            "INNER JOIN CUSTOMER customer ON comment.id_customer = customer.id_customer\n" +
            "INNER JOIN ITEM item ON comment.id_item = item.id_item"
            + " WHERE item.id_item = $1")
    public Flux<Comment> findAllByItem_IdItem(Integer idItem);

    @Query("SELECT customer.* FROM COMMENT comment\n" +
            "INNER JOIN CUSTOMER customer ON comment.id_customer = customer.id_customer\n" +
            " WHERE comment.id_item = $1")
    public Flux<Customer> findAllCustomerByItem_IdItem(Integer idItem);



    @Query("SELECT comment.* FROM COMMENT comment\n" +
            " WHERE comment.id_customer = $1 AND comment.id_item = $2")
    public Flux<Comment> findCommentByCustomer_IdCustomerAndItem_IdItem(Integer idCustomer, Integer idItem);

}