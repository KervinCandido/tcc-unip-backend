package br.unip.cc.tcc.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.unip.cc.tcc.controller.dto.UserDTO;
import br.unip.cc.tcc.controller.form.SignUpForm;
import br.unip.cc.tcc.service.SignUpService;

@RestController
@RequestMapping("/sign-up")
public class SignUpController {
	
	@Autowired
	private SignUpService service;
	
	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody @Valid SignUpForm signUpForm, UriComponentsBuilder uriBuilder) {
		UserDTO createdUser = service.create(signUpForm);
		URI userUri = uriBuilder.path("/user/{id}").buildAndExpand(createdUser.id()).toUri();
		return ResponseEntity.created(userUri).body(createdUser);
	}
}
