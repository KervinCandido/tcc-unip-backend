package br.unip.cc.tcc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unip.cc.tcc.controller.dto.TokenDTO;
import br.unip.cc.tcc.controller.form.AuthForm;
import br.unip.cc.tcc.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> auth(@RequestBody @Valid AuthForm authForm) {
		try {
			TokenDTO tokenDTO = authService.auth(authForm);
			return ResponseEntity.ok(tokenDTO);
		} catch (AuthenticationException  e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
