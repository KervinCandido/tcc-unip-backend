package br.unip.cc.tcc.controller.form;

import java.time.LocalDateTime;
import java.util.List;

import br.unip.cc.tcc.model.Permission;
import br.unip.cc.tcc.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpForm {
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String name;
	
	@NotBlank
	@Size(min = 3, max = 64)
	private String userName;

	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size(min = 6, max = 20)
	private String password;

	public SignUpForm() {}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SignUpForm [name=" + name + ", userName=" + userName + ", email=" + email + "]";
	}

	public User toUser() {
		User user = new User();
		LocalDateTime dataAtual = LocalDateTime.now();
		
		user.setUserName(userName);
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setCreationDate(dataAtual);
		user.setPermissions(List.of(Permission.USER));
		
		return user;
	}
}
