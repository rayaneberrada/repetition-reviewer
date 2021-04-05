# SM-2 calculator

Microservice implementing the SM-2 algorithm used to calculate when an information should be reviewed next to be
remembered in long term memory.

## Getting Started

Once you clone the project you can move inside and run this command to create the docker image or use the Dockerfile:

````
./mvnw spring-boot:build-image
````

# REST API

The REST API to the example app is described below.

## Calculator

### Request

`POST /calculator/nextReview`

    curl -i -H "Content-Type: application/json" -d '{"quality":5, "repetitions":0, "easeFactor":1.3, "interval":0}' http://localhost:8080/calculator/nextReview

### Prerequisites

- Java 11
- Maven 3

## Built With

* [Spring](https://spring.io/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Swagger](https://swagger.io/) - Used to generate the API documentation