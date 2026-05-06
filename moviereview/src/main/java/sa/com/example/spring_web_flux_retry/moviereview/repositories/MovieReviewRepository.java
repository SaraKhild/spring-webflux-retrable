package sa.com.example.spring_web_flux_retry.moviereview.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;
import sa.com.example.spring_web_flux_retry.moviereview.models.MovieReview;

public interface MovieReviewRepository extends ReactiveMongoRepository<MovieReview, String> {

    Flux<MovieReview> findReviewByMovieInfoId(Long MovieInfoId);

}