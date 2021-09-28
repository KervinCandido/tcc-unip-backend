CREATE TABLE profile(
	profile_id BIGINT NOT NULL AUTO_INCREMENT,
	user_id BIGINT NOT NULL,
	profile_name VARCHAR(64) NOT NULL,
	birth_date DATETIME NOT NULL,
	gender VARCHAR(20) NOT NULL,
	photo LONGTEXT,
	description TEXT,
	PRIMARY KEY (profile_id),
	FOREIGN KEY (user_id) REFERENCES user_tcc(user_id)
);
