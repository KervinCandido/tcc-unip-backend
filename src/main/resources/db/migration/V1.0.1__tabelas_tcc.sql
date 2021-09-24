CREATE TABLE user_tcc (
	user_id BIGINT NOT NULL AUTO_INCREMENT,
	username VARCHAR(64) NOT NULL,
	exclusion_date DATETIME,
	PRIMARY KEY(user_id),
	FOREIGN KEY(username) REFERENCES ofUser(username)
);

CREATE TABLE permission(
	permission_id BIGINT NOT NULL AUTO_INCREMENT,
	permission_name VARCHAR(64) NOT NULL,
	PRIMARY KEY(permission_id)
);

CREATE TABLE user_permission (
	user_id BIGINT NOT NULL,
	permission_id BIGINT NOT NULL,
	PRIMARY KEY(user_id, permission_id),
	FOREIGN KEY(user_id) REFERENCES user_tcc(user_id),
	FOREIGN KEY(permission_id) REFERENCES permission(permission_id)
);

