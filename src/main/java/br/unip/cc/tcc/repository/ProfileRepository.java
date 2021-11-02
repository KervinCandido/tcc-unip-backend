package br.unip.cc.tcc.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.cc.tcc.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
	
	Optional<Profile> findByUserId(Long id);

	Optional<Profile> findByUserUserName(String userName);
	
	Page<Profile> findByUserIdNot(Long id, Pageable pageable);
}
