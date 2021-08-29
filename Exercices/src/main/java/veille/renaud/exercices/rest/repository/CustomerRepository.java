package veille.renaud.exercices.rest.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import veille.renaud.exercices.rest.model.Customer;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {

}
