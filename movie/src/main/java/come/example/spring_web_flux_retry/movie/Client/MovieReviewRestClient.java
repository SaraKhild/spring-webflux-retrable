package come.example.spring_web_flux_retry.movie.Client;

import come.example.spring_web_flux_retry.movie.exceptions.MovieReviewClientException;
import come.example.spring_web_flux_retry.movie.exceptions.MovieReviewServerException;
import come.example.spring_web_flux_retry.movie.models.MovieReview;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MovieReviewRestClient {

    private WebClient webClient = WebClient.builder().build();
    private String movieReviewUrl = "http://localhost:8080/get";

    public Flux<MovieReview> getMovieReview(String id) {

        var url = UriComponentsBuilder.fromUriString(movieReviewUrl)
                .queryParam("movieInfoId", id)
                .buildAndExpand().toUriString();
        return this.webClient.get()
                .uri(url)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError, clientResponse -> {
                            return Mono.error(new MovieReviewClientException(
                                    "There is no Movie Review for this id:" + id,
                                    clientResponse.statusCode().value()));
                        })
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        clientResponse -> {
                            return clientResponse.bodyToMono(String.class)
                                    .flatMap(responseMessage -> Mono.error(new MovieReviewServerException("Server Exception" + responseMessage)));
                        }

                )
                .bodyToFlux(MovieReview.class);
    }

}
