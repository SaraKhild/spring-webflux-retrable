package sa.com.example.spring_web_flux_retry.moviereview.routers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import sa.com.example.spring_web_flux_retry.moviereview.handlers.MovieReviewHandler;
import sa.com.example.spring_web_flux_retry.moviereview.models.MovieReview;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MovieReviewRouter {

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/add",
                    method = RequestMethod.POST,
                    beanClass = MovieReviewHandler.class,
                    beanMethod = "add",
                    operation = @Operation(
                            operationId = "addMovieReview",
                            summary = "Add movie review",
                            requestBody = @RequestBody(
                                    required = true,
                                    content = @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = MovieReview.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Movie review added successfully")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/get",
                    method = RequestMethod.GET,
                    beanClass = MovieReviewHandler.class,
                    beanMethod = "get",
                    operation = @Operation(
                            operationId = "getMovieReviews",
                            summary = "Get movie reviews",
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Movie reviews returned successfully")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/put",
                    method = RequestMethod.PUT,
                    beanClass = MovieReviewHandler.class,
                    beanMethod = "update",
                    operation = @Operation(
                            operationId = "updateMovieReview",
                            summary = "Update movie review",
                            requestBody = @RequestBody(
                                    required = true,
                                    content = @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = MovieReview.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Movie review updated successfully")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/delete",
                    method = RequestMethod.DELETE,
                    beanClass = MovieReviewHandler.class,
                    beanMethod = "delete",
                    operation = @Operation(
                            operationId = "deleteMovieReview",
                            summary = "Delete movie review",
                            requestBody = @RequestBody(
                                    required = true,
                                    content = @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = MovieReview.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Movie review deleted successfully")
                            }
                    )
            )
    })
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