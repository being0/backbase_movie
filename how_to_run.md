### Run dependencies by Docker 
To start external dependencies: Keycloak(Auth and Identity server) and Postgres and Kafka and cassandra, 
go to **docker** directory and then run this command 
(Make sure you have given enough ram to your docker engine and it has access to your disk or mount ./docker/keycloak/config to /keycloak/config/):

    $ ./docker-compose up


### Run best picture service
By running the following command on the root of project, You can GET on http://localhost:8080/movies/bestpictures using a title as parameter.

    $ ./gradlew :movie-bestpicture-service:bootRun

### Run rate service
By running the following command the service will be available at port 8070

    $ ./gradlew :movie-rate-service:bootRun

### Run toprated service
By running the following command the service will be available at port 8060

    $ ./gradlew :movie-toprated-service:bootRun