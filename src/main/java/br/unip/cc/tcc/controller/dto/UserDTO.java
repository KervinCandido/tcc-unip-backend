package br.unip.cc.tcc.controller.dto;

import br.unip.cc.tcc.model.User;

public record UserDTO(Long id, String name, String userName, String email) {

	public UserDTO(User user) {
		this(user.getId(), user.getUserOpenFire().getName(), user.getUsername(), user.getUserOpenFire().getEmail());
	}
}
