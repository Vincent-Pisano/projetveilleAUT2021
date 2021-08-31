package veille.renaud.exercices.fullstack1.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;
import veille.renaud.exercices.fullstack1.handlers.NumHandler;

@Component
public class RouterConfig {

    private final NumHandler numHandler;

    public RouterConfig(NumHandler numHandler) {
        this.numHandler = numHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route(
                RequestPredicates.GET("/num"),
                numHandler::num
        );
    }

}
