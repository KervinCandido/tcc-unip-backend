package br.unip.cc.tcc.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.unip.cc.tcc.config.validation.exception.FormErrorException;
import br.unip.cc.tcc.controller.dto.UserDTO;
import br.unip.cc.tcc.controller.form.SignUpForm;
import br.unip.cc.tcc.model.User;
import br.unip.cc.tcc.repository.UserRepository;

@Service
public class SignUpService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public UserDTO create(SignUpForm signUpForm) {
		Optional<User> alreadyExisting = userRepository.findByUserNameOrEmail(signUpForm.getUserName(), signUpForm.getEmail());
		
		if (alreadyExisting.isPresent()) {
			User existingUser = alreadyExisting.get();
			if (existingUser.getUserName().equals(signUpForm.getUserName())) {
				throw new FormErrorException("userName", "Nome de Usuário já cadastrado");
			} else {
				throw new FormErrorException("email", "Endereço de e-mail já cadastrado");
			}
		}
		
		User user = signUpForm.toUser();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		User userDB = userRepository.save(user);
		
		return new UserDTO(userDB.getId(), userDB.getName(), userDB.getUsername(), userDB.getEmail());
	}
}
