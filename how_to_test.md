1. ### Test best picture service

   #### Example:

        curl -X GET -H 'Content-Type: application/json' -i http://localhost:8080/movies/bestpictures?title=12%20Angry%20Men

   #### Signature:
    ```
    GET
    http://<host>:<port>/movies/bestpictures?title={title}
    ```
   #### Response sample

    ```json
    {
    "id": "tt0050083",
    "title": "12 Angry Men",
    "year": "1957 (30th)",
    "additional_info": "Henry Fonda and Reginald Rose, Producers",
    "won": true
    }
    ```

   | HTTP Code                 | Explanation             |
   | -------------             |:-----------------------------------------------:|
   | 200 (OK)                  | Success get |
   | 404 (Not found)           | Movie is not in the list of best picture nominees     |
   | 400 (Bad Request)         | If title is not between 1 to 50 characters     |

2. ### Test rate service

   #### Example

   make sure both rate & toprated services are running, then first use the following command to take a token:

         curl -ss --data "grant_type=password&client_id=curl&username=reza_user&password=admin" http://localhost:8085/auth/realms/spring-security-example/protocol/openid-connect/token

   Then use the token to call the API:

             curl -X POST -H 'Content-Type: application/json' -H 'Authorization: bearer [token]' -i http://localhost:8070/movies/tt0050083/users/rate --data '{"rate":"9"}'

   #### Signature:
    ```
    POST
    http://<host>:<port>/movies/{id}/users/rate
    ```
   #### Payload and Response sample

    ```json
    {"rate":9}
    ```

3. ### Test toprated service

   #### Example:
   
       curl -X GET -H 'Content-Type: application/json' -i http://localhost:8060/movies/top10

   #### Signature:
    ```
    GET
    http://<host>:<port>/movies/top10
    ```
   #### Response sample

    ```json
    {
    "result": [
        {
            "id": "tt0050083",
            "title": "12 Angry Men",
            "rate": 9.0,
            "rate_count": 1,
            "box_office_value": 0
        },
        {
            "id": "tt5186714",
            "title": "The Salesman",
            "rate": 8.0,
            "rate_count": 2,
            "box_office_value": 0
        },
        {
            "id": "tt1375666",
            "title": "Inception",
            "rate": 0.0,
            "rate_count": 0,
            "box_office_value": 0
        },
        {
            "id": "tt0133093",
            "title": "The Matrix",
            "rate": 0.0,
            "rate_count": 0,
            "box_office_value": 0
        },
        {
            "id": "tt0047478",
            "title": "Seven Samurai",
            "rate": 0.0,
            "rate_count": 0,
            "box_office_value": 0
        },
        {
            "id": "tt0211915",
            "title": "Amélie",
            "rate": 0.0,
            "rate_count": 0,
            "box_office_value": 0
        },
        {
            "id": "tt0169547",
            "title": "American Beauty",
            "rate": 0.0,
            "rate_count": 0,
            "box_office_value": 0
        },
        {
            "id": "tt0120382",
            "title": "The Truman Show",
            "rate": 0.0,
            "rate_count": 0,
            "box_office_value": 0
        },
        {
            "id": "tt0054215",
            "title": "Psycho",
            "rate": 0.0,
            "rate_count": 0,
            "box_office_value": 0
        },
        {
            "id": "tt0172495",
            "title": "Gladiator",
            "rate": 0.0,
            "rate_count": 0,
            "box_office_value": 0
        }
    ]
   } 
   ```
