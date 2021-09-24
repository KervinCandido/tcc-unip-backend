package br.unip.cc.tcc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.cc.tcc.controller.dto.UserDTO;
import br.unip.cc.tcc.model.User;
import br.unip.cc.tcc.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Optional<UserDTO> findUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.map(UserDTO::new);
	}

}
