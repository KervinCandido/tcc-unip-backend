package br.unip.cc.tcc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.cc.tcc.model.RecommendedUser;

public interface RecommendedUserRepository extends JpaRepository<RecommendedUser, String> {

	Page<RecommendedUser> findByUserId(Long userId, Pageable pageable);
	
}
