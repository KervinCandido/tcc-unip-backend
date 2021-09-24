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
import br.unip.cc.tcc.model.UserOpenFire;
import br.unip.cc.tcc.repository.UserOpenFireRepository;
import br.unip.cc.tcc.repository.UserRepository;

@Service
public class SignUpService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserOpenFireRepository userOpenFireRepository;
	
	@Transactional
	public UserDTO create(SignUpForm signUpForm) {
		User user = signUpForm.toUser();
		UserOpenFire userOpenFire = user.getUserOpenFire();
		
		Optional<UserOpenFire> alreadyExisting = userOpenFireRepository.findByUserNameOrEmail(userOpenFire.getUserName(), userOpenFire.getEmail());
		
		if (alreadyExisting.isPresent()) {
			UserOpenFire existingUser = alreadyExisting.get();
			
			if (existingUser.getUserName().equals(userOpenFire.getUserName())) {
				throw new FormErrorException("userName", "Nome de Usuário já cadastrado");
			} else {
				throw new FormErrorException("email", "Endereço de e-mail já cadastrado");
			}
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userOpenFire.setPassword(encoder.encode(userOpenFire.getPassword()));
		user.setUserOpenFire(userOpenFireRepository.save(userOpenFire));
		User userDB = userRepository.save(user);
		
		return new UserDTO(userDB.getId(), 
				userDB.getUserOpenFire().getName(), 
				userDB.getUsername(), 
				userDB.getUserOpenFire().getEmail());
	}
}
