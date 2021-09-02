package veille.renaud.exercices.fullstack1.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SearchHandlerImpl implements SearchHandler{

    private static final Random random = new Random();

    @Override
    public Mono<ServerResponse> search(ServerRequest serverRequest) {
        Flux<String> results = Flux.merge(
               searchSupplier("Supplier 1"),
               searchSupplier("Supplier 2"),
               searchSupplier("Supplier 3"),
               searchSupplier("Supplier 4")
        );
        return ServerResponse.ok().header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(results, String.class);
    }

    private Flux<String> searchSupplier(String supplierName) {
        List<String> results = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            double randomValue = 10 + (99-10) * random.nextDouble();
            //à revérifier
            BigDecimal value = BigDecimal.valueOf(randomValue).setScale(2, 4);
            results.add(value + " - " + supplierName);
        }
        return Flux.interval(Duration.ofMillis(random.nextInt(1000)))
                .zipWithIterable(results)
                //.map(res -> res.getT2())
                .map(Tuple2::getT2);
    }
}
