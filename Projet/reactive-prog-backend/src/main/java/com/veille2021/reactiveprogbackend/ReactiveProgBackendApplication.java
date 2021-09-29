package com.veille2021.reactiveprogbackend;

import com.veille2021.reactiveprogbackend.configuration.PostgresConfig;
import com.veille2021.reactiveprogbackend.converter.CommentConverter;
import com.veille2021.reactiveprogbackend.model.*;
import com.veille2021.reactiveprogbackend.repository.CommentRepository;
import com.veille2021.reactiveprogbackend.repository.CustomerRepository;
import com.veille2021.reactiveprogbackend.repository.ItemRepository;
import com.veille2021.reactiveprogbackend.repository.OrderRepository;
import com.veille2021.reactiveprogbackend.service.Service;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
//@EnableR2dbcRepositories
@Import(PostgresConfig.class)
public class ReactiveProgBackendApplication implements CommandLineRunner {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Service service;

    DatabaseClient databaseClient;

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(ReactiveProgBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        databaseClient = DatabaseClient.builder().connectionFactory(new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration
                .builder()
                .host("localhost")
                .database("postgres")
                .username("postgres")
                .schema("public")
                .password(env.getProperty("database.password"))
                .port(5432)
                .build())).build();
        //CommentConverter converter = new CommentConverter();
        //Integer idItem = 95;

        //commentRepository.findAllByItem_IdItem(idItem).subscribe(System.out::println);

        //findCommentFromItem(idItem).subscribe(System.out::println);


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

        /*Item item1 = new Item();
        item1.setName("Test1");
        item1.setPrice(new BigDecimal("2.35"));
        item1.setType("TEST");
        item1.setQuantity(0);*/
        /*Mono.just(getOrder())
                .map(order -> {
                    System.out.println(order);
                    return orderRepository.save(order);
                })
                .hasElement().subscribe(System.out::println);*/
        //Mono.just(getOrder())
        // .map(service::createOrder)
        // .flatMap(orderRepository::save)
        // .subscribe(System.out::println);

        //databaseClient.sql("INSERT INTO public.order (total_price, status) VALUES ($1, $2)");
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

    public Order getOrder() {
        Item item1 = new Item();
        item1.setIdItem(101);
        item1.setName("Test1");
        item1.setPrice(new BigDecimal("2.35"));
        item1.setType("TEST");
        item1.setQuantity(13);

        List<Item> items = new ArrayList<>();
        //Item[] items = {item1};

        Customer customer = new Customer();
        customer.setIdCustomer(101);
        customer.setUsername("Username123");
        customer.setPassword("Pasword123");

        Order order = new Order();
        //order.setCustomer(customer);
        order.setStatus(Order.OrderStatus.PROCESSING);
        //order.setItems(items);
        //order.setItems(items);
        order.setTotalPrice(new BigDecimal(0));
        //System.out.println(order);
        return order;
    }
}
