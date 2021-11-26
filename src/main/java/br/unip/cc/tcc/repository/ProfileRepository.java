package br.unip.cc.tcc.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.unip.cc.tcc.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
	
	Optional<Profile> findByUserId(Long id);

	Optional<Profile> findByUserUserName(String userName);
	
	Page<Profile> findByUserIdNot(Long id, Pageable pageable);
	
	@Query(
	"""
		SELECT p FROM Profile p
			WHERE 
		p.user.id != :id 
		AND p.user.id NOT IN(
			SELECT c.user.id FROM Contact c WHERE (c.user.id = :id AND c.userContact.id = p.user.id)
				OR (c.user.id = p.user.id AND c.userContact.id = :id)
		)
		AND p.user.id not IN(
			SELECT rc.requested.id FROM RequestContact rc WHERE rc.requester.id = :id AND rc.status = 'PENDENTE'
		)
	""")
	Page<Profile> findNewContacts(@Param("id") Long id, Pageable pageable);
}
