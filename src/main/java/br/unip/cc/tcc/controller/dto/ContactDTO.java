package br.unip.cc.tcc.controller.dto;

import br.unip.cc.tcc.model.Profile;

public record ContactDTO (
		String userName, 
		String photoProfile, 
		String profileName
) {
	public ContactDTO(Profile profile) {
		this(profile.getUser().getUsername(), profile.getPhoto(), profile.getProfileName());
	}
	
}
