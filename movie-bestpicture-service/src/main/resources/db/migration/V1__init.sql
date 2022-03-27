
CREATE TABLE IF NOT EXISTS best_picture_nominee (
    id varchar(50) NOT NULL,
    title varchar(50) NOT NULL,
    year varchar(20) NOT NULL,
    additional_info varchar(255) NOT NULL,
    won boolean NOT NULL,
    PRIMARY KEY (id)
    );

INSERT INTO best_picture_nominee(id, title, year, additional_info, won) values('tt0169547', 'American Beauty', '1999 (72nd)', 'Bruce Cohen and Dan Jinks, Producers', 'YES');
INSERT INTO best_picture_nominee(id, title, year, additional_info, won) values('tt0050083', '12 Angry Men', '1957 (30th)', 'Henry Fonda and Reginald Rose, Producers', 'NO');