package com.veille2021.reactiveprogbackend;

import com.veille2021.reactiveprogbackend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ReactiveProgBackendApplication implements CommandLineRunner {

    @Autowired
    private CommentRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(ReactiveProgBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.findAllByIdItem_IdItem(95).subscribe(System.out::println);
    }
}
