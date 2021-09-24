package br.unip.cc.tcc.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthForm {
	
	@NotEmpty
	@Size(min=3, max=64)
	private String userName;
	
	@NotEmpty
	@Size(min=6, max=20)
	private String password;
	
	public AuthForm() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken() {
		return new UsernamePasswordAuthenticationToken(userName, password);
	}
}
