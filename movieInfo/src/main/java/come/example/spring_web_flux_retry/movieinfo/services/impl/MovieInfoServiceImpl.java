package come.example.spring_web_flux_retry.movieinfo.services.impl;

import come.example.spring_web_flux_retry.movieinfo.models.MovieInfo;
import come.example.spring_web_flux_retry.movieinfo.repositories.MovieInfoRepository;
import come.example.spring_web_flux_retry.movieinfo.services.MovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieInfoServiceImpl implements MovieInfoService {

    @Autowired
    private MovieInfoRepository movieInfoRepository;

    @Override
    public Mono<MovieInfo> addMovieInfo(MovieInfo model) {
        return this.movieInfoRepository.save(model);
    }

    @Override
    public Flux<MovieInfo> getAllMovieInfo() {
        return this.movieInfoRepository.findAll();
    }

    @Override
    public Mono<MovieInfo> getMovieInfoById(String id) {
        return this.movieInfoRepository.findById(id);
    }

    @Override
    public Flux<MovieInfo> getMovieInfoByYear(int year) {
        return movieInfoRepository.findByYear(year);
    }

    @Override
    public Mono<MovieInfo> putMovieInfo(MovieInfo model, String id) {
        return this.getMovieInfoById(id)
                .flatMap(movie -> {
                    movie.setName(model.getName());
                    movie.setYear(model.getYear());
                    return movieInfoRepository.save(movie);
                });
    }

    @Override
    public Mono<Void> deleteMovieInfo(String id) {
        return this.movieInfoRepository.deleteById(id);
    }
}
