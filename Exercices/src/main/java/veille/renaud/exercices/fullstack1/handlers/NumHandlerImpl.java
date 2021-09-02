package veille.renaud.exercices.fullstack1.handlers;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.*;
import static reactor.core.publisher.Mono.*;

@Service
public class NumHandlerImpl implements NumHandler {

    @Override
    public Mono<ServerResponse> num(ServerRequest serverRequest) {
        System.out.println("test");
        return ok().body(just(100), Integer.class);
    }
}
