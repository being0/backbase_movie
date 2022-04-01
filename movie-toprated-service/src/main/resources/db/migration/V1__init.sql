
-- To keep the write quick, don't define any index in this table
-- this table should only be queried periodically
CREATE TABLE IF NOT EXISTS movie_rate (
    id varchar(50) NOT NULL,
    title varchar(50) NOT NULL,
    rate float8 NOT NULL DEFAULT 0,
    rate_count bigint NOT NULL DEFAULT 0,
    box_office_value bigint NOT NULL DEFAULT 0,
    modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
    );

CREATE MATERIALIZED VIEW top10_rated_movie
AS
    SELECT id, title, ROUND(rate::numeric, 1) AS rate, rate_count, box_office_value, modified
    FROM movie_rate
    ORDER BY ROUND(rate::numeric, 1) DESC, box_office_value DESC
    LIMIT 10
WITH DATA;

CREATE TABLE shedlock(name VARCHAR(64) NOT NULL, lock_until TIMESTAMP NOT NULL,
                      locked_at TIMESTAMP NOT NULL, locked_by VARCHAR(255) NOT NULL, PRIMARY KEY (name));

-- The table should be normally updated by a consumer listening to movie CRUD service events(that is not part of this challenge)(or it can be updated by other methods)
INSERT INTO movie_rate(id, title) values('tt0050083', '12 Angry Men');
INSERT INTO movie_rate(id, title) values('tt0169547', 'American Beauty');
INSERT INTO movie_rate(id, title) values('tt0137523', 'Fight Club');
INSERT INTO movie_rate(id, title) values('tt1375666', 'Inception');
INSERT INTO movie_rate(id, title) values('tt0133093', 'The Matrix');
INSERT INTO movie_rate(id, title) values('tt0047478', 'Seven Samurai');
INSERT INTO movie_rate(id, title) values('tt0211915', 'Amélie');
INSERT INTO movie_rate(id, title) values('tt1832382', 'A Separation');
INSERT INTO movie_rate(id, title) values('tt0120382', 'The Truman Show');
INSERT INTO movie_rate(id, title) values('tt0054215', 'Psycho');
INSERT INTO movie_rate(id, title) values('tt0172495', 'Gladiator');
INSERT INTO movie_rate(id, title) values('tt5186714', 'The Salesman');
REFRESH MATERIALIZED VIEW top10_rated_movie;