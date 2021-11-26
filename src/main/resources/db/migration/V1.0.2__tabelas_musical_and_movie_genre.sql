CREATE TABLE musical_genre(
	musical_genre_id BIGINT NOT NULL AUTO_INCREMENT,
	genre_name VARCHAR(64) NOT NULL,
	PRIMARY KEY(musical_genre_id)
);

CREATE TABLE movie_genre(
	movie_genre_id BIGINT NOT NULL AUTO_INCREMENT,
	genre_name VARCHAR(64) NOT NULL,
	PRIMARY KEY(movie_genre_id)
);

CREATE TABLE profile_musical_genre(
	profile_id BIGINT NOT NULL,
	musical_genre_id BIGINT NOT NULL,
	FOREIGN KEY (profile_id) REFERENCES profile(profile_id),
	FOREIGN KEY (musical_genre_id) REFERENCES musical_genre(musical_genre_id),
	PRIMARY KEY (profile_id, musical_genre_id)
);

CREATE TABLE profile_movie_genre(
	profile_id BIGINT NOT NULL,
	movie_genre_id BIGINT NOT NULL,
	FOREIGN KEY (profile_id) REFERENCES profile(profile_id),
	FOREIGN KEY (movie_genre_id) REFERENCES movie_genre(movie_genre_id),
	PRIMARY KEY (profile_id, movie_genre_id)
);

insert into musical_genre (genre_name) values  ('Axé'), ('Blues'), ('Country'), ('Eletrônica'), ('Forró'), ('Funk'), ('Gospel'), ('Heavy Metal'), ('Hip Hop'), ('J-Pop'), ('J-Rock'), ('Jazz'), ('K-Pop'), ('K-Rock'), ('MPB'), ('Música clássica'), ('Pagode'), ('Pop'), ('Rap'), ('Reggae'), ('Rock'), ('Rock clássico'), ('Samba'), ('Sertanejo'), ('Tecnopop');


insert into movie_genre (genre_name) values ('Ação'),('Aventura'), ('Biográfico'), ('Cinema de arte'), ('Chanchada'), ('Comédia'), ('Comédia de ação'), ('Comédia de terror'), ('Comédia dramática'), ('Comédia romântica'), ('Dança'), ('Documentário'), ('Docuficção'), ('Drama'), ('Espionagem'), ('Faroeste'), ('Fantasia'), ('Fantasia científica'), ('Ficção científica'), ('Filmes com truques'), ('Filmes de guerra'), ('Histórico'), ('Mistério'), ('Musical'), ('Filme policial'), ('Romance'), ('Suspense'), ('Terror');
