package come.example.spring_web_flux_retry.movie.exceptions;

public class MovieInfoClientException extends RuntimeException {

    private String message;
    private Integer statusCode;

    public MovieInfoClientException(String message, Integer statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }

    public MovieInfoClientException(String message) {
        super(message);
        this.message = message;
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
