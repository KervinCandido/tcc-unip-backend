package br.unip.cc.tcc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unip.cc.tcc.controller.dto.UserDTO;
import br.unip.cc.tcc.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
		Optional<UserDTO> userDTO = userService.findUserById(id);
		
		if (userDTO.isPresent()) {
			return ResponseEntity.ok(userDTO.get());
		}
		
		return ResponseEntity.notFound().build();
	}
}
