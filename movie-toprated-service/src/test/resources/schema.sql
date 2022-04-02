CREATE TABLE IF NOT EXISTS movie_rate (
    id varchar(50) NOT NULL,
    title varchar(50) NOT NULL,
    rate float8 NOT NULL DEFAULT 0,
    rate_count bigint NOT NULL DEFAULT 0,
    box_office_value bigint NOT NULL DEFAULT 0,
    modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
    );
