package come.example.spring_web_flux_retry.movieinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
public class MovieInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieInfoApplication.class, args);
	}

}
