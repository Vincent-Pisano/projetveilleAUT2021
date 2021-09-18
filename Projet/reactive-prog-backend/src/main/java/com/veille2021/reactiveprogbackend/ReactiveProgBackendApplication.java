package com.veille2021.reactiveprogbackend;

import com.veille2021.reactiveprogbackend.converter.CommentConverter;
import com.veille2021.reactiveprogbackend.model.Comment;
import com.veille2021.reactiveprogbackend.model.Customer;
import com.veille2021.reactiveprogbackend.repository.CommentRepository;
import com.veille2021.reactiveprogbackend.repository.CustomerRepository;
import com.veille2021.reactiveprogbackend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ReactiveProgBackendApplication implements CommandLineRunner {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReactiveProgBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        CommentConverter converter = new CommentConverter();
        Integer idItem = 95;

        commentRepository.findAllByItem_IdItem(idItem).subscribe(System.out::println);

        findCommentFromItem(idItem).subscribe(System.out::println);


        //Flux<Integer> childCompanyIds = commentRepository.findCustomerByItem_IdItem(95);
        //childCompanyIds.subscribe(System.out::println);

        /*commentRepository.findAllCustomerByItem_IdItem(idItem)
                .flatMap(idCustomer ->
                        customerRepository.findById(idCustomer)
                )
                .flatMap(customer ->
                        commentRepository.findCommentByCustomer_IdCustomerAndItem_IdItem(
                                customer.getIdCustomer(), idItem)
                        )
                .subscribe(System.out::println);*/
    }

    public Flux<Comment> findCommentFromItem(Integer idItem){
        return commentRepository.findAllByItem_IdItem(idItem)
                .flatMap(comment ->
                        Mono.just(comment)
                                .zipWith(customerRepository.findCustomerByIdComment(comment.getIdComment()))
                                .map(tupla -> tupla.getT1().withCustomer(tupla.getT2()))
                                .zipWith(itemRepository.findById(idItem))
                                .map(tupla -> tupla.getT1().withItem(tupla.getT2()))
                );
    }
}
