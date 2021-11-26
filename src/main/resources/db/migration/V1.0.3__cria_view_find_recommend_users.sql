CREATE OR REPLACE view find_recommend_users AS
SELECT 
	uuid() as UUID,
	p1.user_id,
	p2.user_id AS recommended_user_id,
	u.username AS recommended_username,
	p2.profile_id AS recommended_profile_id, 
	p2.profile_name AS recommended_profile_name, 
	p2.birth_date AS recommended_birth_date, 
	p2.gender AS recommended_gender, 
	p2.photo AS recommended_photo, 
	p2.description AS recommended_description
FROM 
	profile p1,
	profile p2
		INNER JOIN user_tcc u
	ON p2.user_id = u.user_id
WHERE 1=1
	AND p2.user_id <> p1.user_id
	AND p1.user_id NOT IN (SELECT user_id_contact FROM contact WHERE user_id = p2.user_id)
	AND p2.user_id NOT IN (SELECT user_requested FROM request_contact WHERE user_requester = p1.user_id AND status <> 'PENDENTE')
	AND p1.user_id NOT IN (SELECT user_requested FROM request_contact WHERE user_requester = p2.user_id AND status <> 'PENDENTE')
	AND (p2.profile_id IN (SELECT profile_id FROM profile_musical_genre WHERE musical_genre_id in (SELECT musical_genre_id FROM profile_musical_genre WHERE profile_id = p1.profile_id))
	OR p2.profile_id IN (SELECT profile_id FROM profile_movie_genre WHERE movie_genre_id in (SELECT movie_genre_id FROM profile_movie_genre WHERE profile_id = p1.profile_id)));