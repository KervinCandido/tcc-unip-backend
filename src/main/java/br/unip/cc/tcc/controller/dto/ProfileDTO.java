package br.unip.cc.tcc.controller.dto;

import java.time.LocalDate;
import java.util.List;

import br.unip.cc.tcc.model.Profile;

public record ProfileDTO(Long id, Long userId, String profileName, LocalDate birthDate, String gender, String photo,
		String description, List<FavoriteMusicalGenreDTO> favoriteMusicalGenreDTO, List<FavoriteMovieGenreDTO> favoriteMovieGenreDTO) {

	public ProfileDTO(Profile profile) {
		this(profile.getId(), profile.getUser().getId(), profile.getProfileName(), profile.getBirthDate(),
				profile.getGender().toString(), profile.getPhoto(), profile.getDescription(), 
				profile.getFavoriteMusicGenre().stream().map(FavoriteMusicalGenreDTO::new).toList(),
				profile.getFavoriteMovieGenre().stream().map(FavoriteMovieGenreDTO::new).toList());
	}
}
