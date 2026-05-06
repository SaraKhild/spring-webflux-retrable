package sa.com.example.spring_web_flux_retry.moviereview.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import sa.com.example.spring_web_flux_retry.moviereview.handlers.MovieReviewHandler;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MovieReviewRouter {

    @Bean
    public RouterFunction<ServerResponse> mvoiewReviewRouter(MovieReviewHandler handler) {
        return route()
                .POST("/add", request -> handler.add(request))
                .GET("/get", request -> handler.get(request))
                .PUT("/put", request -> handler.update(request))
                .DELETE("/delete", request -> handler.delete(request))
                .build();

        // nesting enpoints
        //   return route()
        //   .nest(path("/movie-review"), builder ->{
        //     builder.POST("/add", request-> handler.add(request))
        //             .GET("/get", request-> handler.get(request));

        //   })
        //   .build();
    }

}