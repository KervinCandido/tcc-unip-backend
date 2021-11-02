CREATE TABLE user_tcc (
	user_id BIGINT NOT NULL AUTO_INCREMENT,
	username VARCHAR(64) NOT NULL,
	name VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	password VARCHAR(255) NOT NULL,
	creation_date DATETIME DEFAULT NOW(),
	modification_date DATETIME,
	exclusion_date DATETIME,
	PRIMARY KEY(user_id)
);

CREATE TABLE permission(
	permission_id BIGINT NOT NULL AUTO_INCREMENT,
	permission_name VARCHAR(64) NOT NULL,
	PRIMARY KEY(permission_id)
);

INSERT INTO permission (permission_id, permission_name) values (1, "ADMIN");
INSERT INTO permission (permission_id, permission_name) values (2, "USER");

CREATE TABLE user_permission (
	user_id BIGINT NOT NULL,
	permission_id BIGINT NOT NULL,
	PRIMARY KEY(user_id, permission_id),
	FOREIGN KEY(user_id) REFERENCES user_tcc(user_id),
	FOREIGN KEY(permission_id) REFERENCES permission(permission_id)
);

CREATE TABLE profile(
	profile_id BIGINT NOT NULL AUTO_INCREMENT,
	user_id BIGINT NOT NULL,
	profile_name VARCHAR(64) NOT NULL,
	birth_date DATETIME NOT NULL,
	gender VARCHAR(20) NOT NULL,
	photo VARCHAR(100),
	description TEXT,
	PRIMARY KEY (profile_id),
	FOREIGN KEY (user_id) REFERENCES user_tcc(user_id)
);

CREATE TABLE contact (
	user_id BIGINT NOT NULL,
	user_id_contact BIGINT NOT NULL,
	PRIMARY KEY(user_id, user_id_contact),
	FOREIGN KEY(user_id) REFERENCES user_tcc(user_id),
	FOREIGN KEY(user_id_contact) REFERENCES user_tcc(user_id)
);

