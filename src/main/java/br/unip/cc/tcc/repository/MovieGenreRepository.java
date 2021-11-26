package br.unip.cc.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.cc.tcc.model.MovieGenre;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {
}
