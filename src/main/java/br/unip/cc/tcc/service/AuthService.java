package br.unip.cc.tcc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.unip.cc.tcc.config.security.TokenService;
import br.unip.cc.tcc.controller.dto.TokenDTO;
import br.unip.cc.tcc.controller.form.AuthForm;

@Service
public class AuthService {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	public TokenDTO auth(AuthForm authForm) {
		UsernamePasswordAuthenticationToken authToken = authForm.toUsernamePasswordAuthenticationToken();
		Authentication authentication = authManager.authenticate(authToken);
		return new TokenDTO(TokenDTO.TYPE_BEARER, tokenService.gerarToken(authentication));
	}

}
