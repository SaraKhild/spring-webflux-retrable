package sa.com.example.spring_web_flux_retry.moviereview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "sa.com.example.spring_web_flux_retry.moviereview.repositories")
public class MoviereviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviereviewApplication.class, args);
	}

}
