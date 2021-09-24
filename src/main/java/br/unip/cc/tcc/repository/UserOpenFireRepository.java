package br.unip.cc.tcc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.cc.tcc.model.UserOpenFire;

public interface UserOpenFireRepository extends JpaRepository<UserOpenFire, String> {
	
	Optional<UserOpenFire> findByUserNameOrEmail(String userName, String email);
}
