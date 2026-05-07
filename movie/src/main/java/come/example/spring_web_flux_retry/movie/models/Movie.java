package come.example.spring_web_flux_retry.movie.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private MovieInfo movieInfo;
    private List<MovieReview> MovieReviewm;
}