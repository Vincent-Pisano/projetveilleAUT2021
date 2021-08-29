package veille.renaud.exercices.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import veille.renaud.exercices.rest.model.Customer;
import veille.renaud.exercices.rest.repository.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @GetMapping
    public Flux<Customer> getCustomers(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Customer> getCustomer(@PathVariable Integer id) {
        return repository.findById(id);
    }

    @PostMapping
    public Mono<Customer> createCustomer(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @PutMapping("/{id}")
    public Mono<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Integer id) {
        return repository.findById(id).map(
                (c) -> {
                    if (customer.getName() != null) {
                        c.setName(customer.getName());
                    } else {
                        c.setName("");
                    }
                    return c;
                }
        ).flatMap(c -> repository.save(c));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCustomer(@PathVariable Integer id) {
        return repository.deleteById(id);
    }
}
