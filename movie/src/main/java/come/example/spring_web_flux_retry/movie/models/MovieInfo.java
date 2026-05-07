package come.example.spring_web_flux_retry.movie.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieInfo {
    private String movieInfoId;
    private String name;
    private Integer year;
}
