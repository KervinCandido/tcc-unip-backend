package br.unip.cc.tcc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.cc.tcc.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
		
	Optional<User> findByUserName(String userName);

	Optional<User> findByUserNameOrEmail(String userName, String email);
}
