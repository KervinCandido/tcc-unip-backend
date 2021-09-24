package br.unip.cc.tcc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.unip.cc.tcc.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT utcc FROM User utcc WHERE utcc.userOpenFire.userName = :userName")
	Optional<User> findByUserName(@Param("userName") String userName);
}
