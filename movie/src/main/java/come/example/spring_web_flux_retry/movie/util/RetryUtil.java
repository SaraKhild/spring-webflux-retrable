package come.example.spring_web_flux_retry.movie.util;

import come.example.spring_web_flux_retry.movie.exceptions.MovieInfoServerException;
import come.example.spring_web_flux_retry.movie.exceptions.MovieReviewServerException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.Exceptions;
import reactor.util.retry.Retry;

import java.time.Duration;

public class RetryUtil {

    public static Retry retrySpec() {

        return Retry.fixedDelay(3, Duration.ofMinutes(5))
                .filter(ex -> {
                    System.out.println("exception type= " + ex.getClass());
                    return ex instanceof MovieInfoServerException
                            || ex instanceof MovieReviewServerException
                            || ex instanceof WebClientRequestException;
                })
                .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) ->
                        Exceptions.propagate(retrySignal.failure())
                ));
    }
}
