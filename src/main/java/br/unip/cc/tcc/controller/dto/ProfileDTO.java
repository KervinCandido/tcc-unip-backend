package br.unip.cc.tcc.controller.dto;

import java.time.LocalDate;

import br.unip.cc.tcc.model.Profile;

public record ProfileDTO(Long id, Long userId, String profileName, LocalDate birthDate, String gender, String photo,
		String description) {

	public ProfileDTO(Profile profile) {
		this(profile.getId(), profile.getUser().getId(), profile.getProfileName(), profile.getBirthDate(),
				profile.getGender().toString(), profile.getPhoto(), profile.getDescription());
	}
}
