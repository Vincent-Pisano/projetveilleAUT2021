package veille.renaud.exercices.fullstack1.handlers;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface NumHandler {

    Mono<ServerResponse> num(ServerRequest serverRequest);

}
