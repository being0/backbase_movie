
CREATE TABLE IF NOT EXISTS movie_rate (
    id varchar(50) NOT NULL,
    title varchar(50) NOT NULL,
    rate float8 NOT NULL DEFAULT 0,
    rate_count bigint NOT NULL DEFAULT 0,
    box_office_value bigint NOT NULL DEFAULT 0,
    modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS top10_rated_movies (
    id varchar(50) NOT NULL,
    title varchar(50) NOT NULL,
    rate numeric(2,1) NOT NULL DEFAULT 0,
    rate_count bigint NOT NULL DEFAULT 0,
    box_office_value bigint NOT NULL DEFAULT 0,
    modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
    );

CREATE TABLE shedlock(name VARCHAR(64) NOT NULL, lock_until TIMESTAMP NOT NULL,
                      locked_at TIMESTAMP NOT NULL, locked_by VARCHAR(255) NOT NULL, PRIMARY KEY (name));

INSERT INTO movie_rate(id, title) values('tt0169547', 'American Beauty');
INSERT INTO movie_rate(id, title) values('tt0050083', '12 Angry Men');