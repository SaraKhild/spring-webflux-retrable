package come.example.spring_web_flux_retry.movie.exceptions;

public class MovieReviewClientException extends RuntimeException {

    private String message;
    private Integer statusCode;

    public MovieReviewClientException(String message, Integer statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

}
