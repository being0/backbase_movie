CREATE
KEYSPACE moviesdb WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE moviesdb.movie
(
    id               varchar,
    title            varchar,
    rate             double,
    rate_counter     counter,
    box_office_value bigint,
    modified         timestamp,
    PRIMARY KEY (id)
);

-- movies sample data
INSERT INTO moviesdb.movie (id, title, box_office_value)
VALUES ('tt0169547', 'American Beauty', 3000000)