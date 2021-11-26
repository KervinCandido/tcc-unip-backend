package br.unip.cc.tcc.controller.dto;

import br.unip.cc.tcc.model.Profile;
import br.unip.cc.tcc.model.RecommendedUser;

public record ContactDTO (
		String userName, 
		String photoProfile, 
		String profileName
) {
	public ContactDTO(Profile profile) {
		this(profile.getUser().getUsername(), profile.getPhoto(), profile.getProfileName());
	}
	
	public ContactDTO(RecommendedUser recommendedUser) {
		this(recommendedUser.getUserName(), recommendedUser.getPhoto(), recommendedUser.getProfileName());
	}
}
