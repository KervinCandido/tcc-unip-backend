package br.unip.cc.tcc.controller.dto;

import br.unip.cc.tcc.model.MovieGenre;

public record FavoriteMovieGenreDTO(Long id, String name) {
	public FavoriteMovieGenreDTO(MovieGenre movieGenre) {
		this(movieGenre.getId(), movieGenre.getName());
	}
}
