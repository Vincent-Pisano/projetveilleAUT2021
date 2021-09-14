package com.veille2021.reactiveprogbackend.repository;

import com.veille2021.reactiveprogbackend.model.Comment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CommentRepository extends ReactiveCrudRepository<Comment, Integer> {

    public Flux<Comment> findAllByIdItem_IdItem(Integer idItem);

}