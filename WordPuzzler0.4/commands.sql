

CREATE TABLE players (
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR (250) NOT NULL UNIQUE,
highscore INT NULL,
PRIMARY KEY (id),
FOREIGN KEY (name) REFERENCES logins (username)
);


CREATE TABLE logins (
id INT NOT NULL AUTO_INCREMENT,
username VARCHAR (250) NOT NULL UNIQUE,
password VARCHAR (15) NOT NULL,
PRIMARY KEY (id)
);



CREATE TABLE gameData (
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR (250) NOT NULL,
score INT NULL,
matchStart DATETIME NOT NULL,
matchEnd DATETIME NOT NULL,
duration LONG NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (name) REFERENCES players (name)
);




  1 | Gustav  |       200 |
|  2 | Vegeta  |       140 |
|  3 | Nicolas |        10 |

UPDATE player set highscore = ? WHERE name = ?

delete  from  logins where username = 'melanie';
INSERT INTO players (name, highscore) VALUES ('nicolas', 10);

INSERT INTO gameData (name, score, matchStart, matchEnd, duration) VALUES ('Vegeta', 240, '2021-06-10 9:30:00', '2021-06-10 10:00:00', 300000);

select * from players join gameData on players.name = gameData.name;

SELECT * FROM players WHERE name = 'vegeta';