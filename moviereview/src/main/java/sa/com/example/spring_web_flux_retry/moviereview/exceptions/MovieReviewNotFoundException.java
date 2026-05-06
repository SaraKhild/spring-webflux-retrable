package sa.com.example.spring_web_flux_retry.moviereview.exceptions;

public class MovieReviewNotFoundException extends RuntimeException {

    private String message;
    private Throwable ex;

    public MovieReviewNotFoundException(String message) {
        super(message);
        this.message = message;
    }

}