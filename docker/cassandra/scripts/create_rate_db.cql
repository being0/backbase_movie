CREATE KEYSPACE ratedb WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE ratedb.movie_user_rate
(
    user_id  varchar,
    movie_id varchar,
    rate     tinyint,
    modified timestamp,
    PRIMARY KEY (user_id, movie_id)
);

