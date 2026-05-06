package come.example.spring_web_flux_retry.movieinfo.services;

import come.example.spring_web_flux_retry.movieinfo.models.MovieInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieInfoService {

    Mono<MovieInfo> addMovieInfo(MovieInfo model);

    Flux<MovieInfo> getAllMovieInfo();

    Mono<MovieInfo> getMovieInfoById(String id);

    Flux<MovieInfo> getMovieInfoByYear(int year);

    Mono<MovieInfo> putMovieInfo(MovieInfo model, String id);

    Mono<Void> deleteMovieInfo(String id);
}
