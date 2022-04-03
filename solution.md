
The solution is consist of three categories of API services:

1. #### Movie Best picture service 

    In order to achieve high availability the list is cached in-memory which can be updated periodically from database(or also other solution like github action or raising a message can be used). The list management has not been implemented in this challenge(cloud solution like Azure Data Factory can be used to read the CSV file and write it into the DB and a raise the update message). This service will be publicly accessible.  
    ```
    GET
    http://<host>:<port>/movies/bestpictures?title={title}"
    ```
   
2. #### Movie rate services

   These services provide crud endpoints for users to rate on movies. These services should be highly available and scalable but it doesn't need strong consistency(eventual consistency is acceptable). Cassandra DB satisfy these requirements and provides high write throughput. UserId has been used as partition key. Considering future requirements, it allows to load all the user rates using the same partition. 
After a rate is sent to this service, the rate is persisted in Cassandra for the user and a message is raised into 
the **kafka movie_rate_topic**. The message later can be consumed by **toprated service** to enrol and calculate the average of each movie rate.
These services need **JWT token** to be included in the header.
Note: It may look over engineering by adding Cassandra to the stack, however I tried to provide a 
middle solution and reduce the technical debts, Also Cassandra mainly added because of its high availability, 
even with small number of users, system should be highly available.

    ```
    POST
    http://<host>:<port>/movies/{id}/users/rate"
    ```
    ```
    GET
    http://<host>:<port>/movies/{id}/users/rate"
    ```
    ```
    DELETE
    http://<host>:<port>/movies/{id}/users/rate"
    ```

4. #### Movie top 10 service 
  
   This service reads top 10 movies from a Postgres materialized view(top10_rated_movie) and cache it in-memory.
   The materialized view and the cache will be updated periodically. Using in-memory cache helps to provide 
high available and high performance system using a simple setup. The materialized view keeps top 10 movies ordered by average user rated and box office value.
   The movies average rates are updated by a consumer that reads user rate messages from **kafka movie_rate_topic**.
   This consumer can be deployed separately by different profile.
   The movie crud service has not been implemented in this challenge and the **movieratedb** DB can be updated by several different methods.
   This service is publicly accessible. 

    ```
    GET
    http://<host>:<port>/movies/top10
    ```