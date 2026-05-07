package come.example.spring_web_flux_retry.movie.controllers;

import come.example.spring_web_flux_retry.movie.Client.MovieInfoRestClient;
import come.example.spring_web_flux_retry.movie.Client.MovieReviewRestClient;
import come.example.spring_web_flux_retry.movie.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieInfoRestClient movieInfoRestClient;
    @Autowired
    private MovieReviewRestClient movieReviewRestClient;

    @GetMapping("/{id}")
    public Mono<Movie> getMovieById(@PathVariable String id) {

        return movieInfoRestClient.getMovieInfo(id).flatMap(movieInfo -> {

            var movieReviewList = movieReviewRestClient.getMovieReview(id).collectList();

            return movieReviewList.map(movieReview -> new Movie(movieInfo, movieReview));
        });

    }

}