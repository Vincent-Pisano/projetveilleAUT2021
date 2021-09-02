package veille.renaud.exercices.fullstack1.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;
import veille.renaud.exercices.fullstack1.handlers.NumHandler;
import veille.renaud.exercices.fullstack1.handlers.SearchHandler;

@Component
public class RouterConfig {

    private final NumHandler numHandler;
    private final SearchHandler searchHandler;

    public RouterConfig(NumHandler numHandler, SearchHandler searchHandler) {
        this.numHandler = numHandler;
        this.searchHandler = searchHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions
                .route(
                    RequestPredicates.GET("/num"),
                    numHandler::num)
                .andRoute(
                    RequestPredicates.GET("/search"),
                    searchHandler::search);
    }

}
