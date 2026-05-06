package come.example.spring_web_flux_retry.movieinfo.controllers;

import come.example.spring_web_flux_retry.movieinfo.models.MovieInfo;
import come.example.spring_web_flux_retry.movieinfo.services.MovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies-information")
public class MovieInfoController {

    @Autowired
    private MovieInfoService movieInfoService;

    @PostMapping("add-movie-info")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MovieInfo> addMovieInformation(@RequestBody MovieInfo movieInfo) {

        return movieInfoService.addMovieInfo(movieInfo);
    }

    @GetMapping("get-all-movie-info")
    public Flux<MovieInfo> getAllMovieInformation() {

        return movieInfoService.getAllMovieInfo();
    }

    @GetMapping("get-movie-info-by-id/{id}")
    public Mono<ResponseEntity<MovieInfo>> getAllMovieInformation(@PathVariable String id) {
        return movieInfoService.getMovieInfoById(id).map(movieInfo -> ResponseEntity.ok()
                        .body(movieInfo))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    // @GetMapping("get-movie-info-by/{id}")
    // public Mono<ResponseEntity<MovieInfo>> getAllMovieInformation2(@PathVariable
    // String id){
    // return movieInfoService.getMovieInfoById(id)
    // .map(movieInfo-> ResponseEntity.ok()
    // .body(movieInfo))
    // .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    // }

    @GetMapping("/get-movie-info-by-year")
    public Flux<ResponseEntity<MovieInfo>> getMovieInfosByYear(
            @RequestParam(value = "year", required = false) Integer year) {

        return movieInfoService.getMovieInfoByYear(year)
                .map(ResponseEntity.ok()::body)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @PutMapping("put-movie-info-by-id/{id}")
    public Mono<ResponseEntity<MovieInfo>> putMovieInformation(@RequestBody MovieInfo movieInfo,
                                                               @PathVariable String id) {
        return movieInfoService.putMovieInfo(movieInfo, id)
                .map(ResponseEntity.ok()::body)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("delete-movie-info-by-id/{id}")
    public Mono<Void> deleteMovieInformation(@PathVariable String id) {
        return movieInfoService.deleteMovieInfo(id);
    }

}