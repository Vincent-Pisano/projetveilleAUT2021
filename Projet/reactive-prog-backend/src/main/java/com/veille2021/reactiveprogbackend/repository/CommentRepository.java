package com.veille2021.reactiveprogbackend.repository;

import com.veille2021.reactiveprogbackend.model.Comment;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CommentRepository extends ReactiveCrudRepository<Comment, Integer> {
    @Query("SELECT comment.* "
            + " FROM comment "
            + " WHERE comment.id_item = $1")
    public Flux<Comment> findAllByItem_IdItem(Integer idItem);

}