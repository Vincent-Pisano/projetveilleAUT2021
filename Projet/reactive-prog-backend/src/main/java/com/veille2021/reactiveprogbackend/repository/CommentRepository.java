package com.veille2021.reactiveprogbackend.repository;

import com.veille2021.reactiveprogbackend.model.Comment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CommentRepository extends ReactiveCrudRepository<Comment, Integer> {

}