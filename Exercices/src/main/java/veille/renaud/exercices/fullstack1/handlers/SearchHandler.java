package veille.renaud.exercices.fullstack1.handlers;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SearchHandler {

    Mono<ServerResponse> search(ServerRequest serverRequest);

}
