package come.example.spring_web_flux_retry.movie.Client;

import come.example.spring_web_flux_retry.movie.exceptions.MovieInfoClientException;
import come.example.spring_web_flux_retry.movie.exceptions.MovieInfoServerException;
import come.example.spring_web_flux_retry.movie.models.MovieInfo;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MovieInfoRestClient {

    private WebClient webClient = WebClient.builder().build();
    private String movieInfoUrl = "http://localhost:8082/movies-information/";

    public Mono<MovieInfo> getMovieInfo(String id) {

        return this.webClient.get()
                .uri(movieInfoUrl + "get-movie-info-by-id/" + id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    return Mono.error(new MovieInfoClientException(
                            "There is no Movie info for this id:" + id,
                            clientResponse.statusCode().value()));
                })
                // this is another way wrting code
                // if(clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)){
                // return Mono.error(new MovieInfoClientException(
                // "There is no Movie info for this id:" + id,
                // clientResponse.statusCode().value()));
                // }
                // return clientResponse.bodyToMono(String.class)
                // .flatMap(responseMessage -> Mono.error(new MovieInfoClientException(
                // responseMessage, clientResponse.statusCode().value())));

                // })

                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(responseMessage -> Mono
                                    .error(new MovieInfoServerException(
                                            "Server Exception"
                                                    + responseMessage)));

                })

                .bodyToMono(MovieInfo.class);
    }

}

/* Note:
        If we are only interested in response body entity the using methods retrieve()
        and then bodyToFlux() and bodyToMono() will serve the purpose.

        Else, use the method exchange() which will return the ClientResponse
        which has all the response elements such as status, headers and response body as well.

 */
