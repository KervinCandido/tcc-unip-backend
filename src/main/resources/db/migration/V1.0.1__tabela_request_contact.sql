CREATE TABLE request_contact(
	request_contact_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	user_requester BIGINT NOT NULL,
	user_requested BIGINT NOT NULL,
	status VARCHAR(20),
	FOREIGN KEY(user_requester) REFERENCES user_tcc(user_id),
	FOREIGN KEY(user_requested) REFERENCES user_tcc(user_id)
);