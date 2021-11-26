package br.unip.cc.tcc.controller.dto;

import br.unip.cc.tcc.model.MusicalGenre;

public record FavoriteMusicalGenreDTO(Long id, String name) {
	public FavoriteMusicalGenreDTO(MusicalGenre musicalGenre) {
		this(musicalGenre.getId(), musicalGenre.getName());
	}
}
