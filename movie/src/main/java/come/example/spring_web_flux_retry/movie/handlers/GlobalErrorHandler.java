package come.example.spring_web_flux_retry.movie.handlers;

import come.example.spring_web_flux_retry.movie.exceptions.MovieInfoClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(MovieInfoClientException.class)
    public ResponseEntity<String> handlerClientException(MovieInfoClientException exception) {
        return ResponseEntity.status(exception.getStatusCode()).body(exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class) // general exception
    public ResponseEntity<String> handleRunTimeException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

}
