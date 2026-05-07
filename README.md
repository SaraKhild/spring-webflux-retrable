# 🚀 Spring WebFlux Retryable Integration

A reactive microservices project built with Spring WebFlux demonstrating how to implement retryable integrations using Reactor Retry and WebClient.

---

# 📚 Project Overview

This project simulates communication between multiple microservices using reactive programming.

The application demonstrates how to:

- Handle transient integration failures
- Retry failed external API calls
- Work with `WebClient`
- Handle `4xx` and `5xx` errors differently
- Use reactive MongoDB
- Build non-blocking REST APIs with Spring WebFlux

---

# 🏗️ Microservices Architecture

The project contains 3 services:

## 🎬 Movie Info Service

Responsible for managing movie information.

### Features

- Add movie info
- Get all movies
- Get movie by id
- Update movie info
- Delete movie info

---

## ⭐ Movie Review Service

Responsible for managing movie reviews.

### Features

- Add movie reviews
- Get movie reviews
- Update movie reviews
- Delete movie reviews

---

## 🎥 Movie Service

Aggregates data from:

- Movie Info Service
- Movie Review Service

using `WebClient`.

This service demonstrates the retry mechanism.

---

# ⚡ Technologies Used

- ☕ Java
- 🌱 Spring Boot
- ⚡ Spring WebFlux
- 🔄 Reactor Retry
- 🌐 WebClient
- 🍃 MongoDB
- 📘 Swagger / OpenAPI
- 🐳 Docker
- 📦 Maven

---

# 🧠 Reactive Retry Mechanism

The retry mechanism is implemented using Reactor Retry.

```java
Retry.fixedDelay(3, Duration.ofSeconds(2))
```

---

# ✅ Retry Rules

Retries are triggered only for:

- `MovieInfoServerException`
- `MovieReviewServerException`
- `WebClientRequestException`

---

# ❌ No Retry for Client Errors

Client errors such as:

- `400 Bad Request`
- `404 Not Found`

will NOT trigger retries.

Because these errors are usually caused by invalid requests.

---

# 🔁 Retry Flow

```text
Client Request
      ↓
Movie Service
      ↓
Movie Info / Review Service
      ↓
5xx Error Occurs
      ↓
Retry Attempt 1
      ↓
Retry Attempt 2
      ↓
Retry Attempt 3
      ↓
Throw Original Exception
```

---

# ⚙️ Retry Utility Implementation

```java
public class RetryUtil {

    public static Retry retrySpec() {

        return Retry.fixedDelay(3, Duration.ofSeconds(2))
                .filter(ex -> ex instanceof MovieInfoServerException
                        || ex instanceof MovieReviewServerException
                        || ex instanceof WebClientRequestException)

                .doBeforeRetry(retrySignal ->
                        System.out.println("Retrying... attempt: "
                                + (retrySignal.totalRetries() + 1)))

                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) ->
                        Exceptions.propagate(retrySignal.failure()));
    }
}
```

---

# 🌐 WebClient Integration

The Movie Service communicates with external services using `WebClient`.

Example:

```java
public Mono<MovieInfo> getMovieInfo(String id) {

    return this.webClient.get()
            .uri(movieInfoUrl + "get-movie-info-by-id/" + id)
            .retrieve()

            .onStatus(HttpStatusCode::is4xxClientError,
                    clientResponse -> Mono.error(
                            new MovieInfoClientException(
                                    "There is no Movie info for this id:" + id,
                                    clientResponse.statusCode().value()
                            )
                    )
            )

            .onStatus(HttpStatusCode::is5xxServerError,
                    clientResponse ->
                            clientResponse.bodyToMono(String.class)
                                    .flatMap(responseMessage ->
                                            Mono.error(
                                                    new MovieInfoServerException(
                                                            "Server Exception " + responseMessage
                                                    )
                                            )
                                    )
            )

            .bodyToMono(MovieInfo.class)
            .retryWhen(RetryUtil.retrySpec());
}
```

---

# 🧪 Testing Retry Scenario

To simulate a `500 Internal Server Error`, an exception was manually triggered inside the Movie Info service.

Example:

```java
if(id.equals("1")){
    throw new RuntimeException("Test 500 Error");
}
```

This allows the retry mechanism to be tested properly.

---

# 📸 Retry Console Logs

The following image demonstrates retry attempts being triggered after a server-side failure.

## 🔍 Retry Execution Logs

![Retry Console Logs](images/retry-console.png)

---

# 📸 Swagger API Testing

The following image demonstrates testing the retry scenario using Swagger UI.

## 📘 Swagger Testing

![Swagger Retry Testing](images/swagger-retry.png)

---

# 🍃 MongoDB

MongoDB is used as the database for storing:

- Movie information
- Movie reviews

---

# 🐳 Docker Support

MongoDB is containerized using Docker Compose.

Example:

```yaml
version: "3.8"

services:
  mongodb:
    image: mongo:7
    container_name: mongodb

    ports:
      - "27017:27017"

    environment:
      MONGO_INITDB_ROOT_USERNAME: moviedb
      MONGO_INITDB_ROOT_PASSWORD: password
```

---

# 📘 Swagger / OpenAPI

Swagger UI is used to test all APIs.

Swagger URL:

```text
http://localhost:8080/swagger-ui.html
```

OpenAPI Docs:

```text
http://localhost:8080/v3/api-docs
```

---

# 🚀 How to Run the Project

## 1️⃣ Start MongoDB

```bash
docker-compose up -d
```

---

## 2️⃣ Run Services

Start:

- MovieInfoApplication
- MoviereviewApplication
- MovieApplication

---

## 3️⃣ Open Swagger

```text
http://localhost:8080/swagger-ui.html
```

---

# 💡 Key Learning Points

- Reactive Programming with Spring WebFlux
- Using Reactor Retry
- Retryable integrations
- Handling transient failures
- Working with WebClient
- Exception filtering
- Reactive MongoDB
- Swagger integration
- Dockerized MongoDB

---

# 👩‍💻 Author

Developed as part of reactive programming and Spring WebFlux practice.
