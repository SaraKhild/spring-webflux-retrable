package sa.com.example.spring_web_flux_retry.moviereview.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document
public class MovieReview {

    @Id
    private String reviewId;
    private Long movieInfoId;
    private String comment;
    private Double rating;

}