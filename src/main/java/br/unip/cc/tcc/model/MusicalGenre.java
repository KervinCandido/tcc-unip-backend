package br.unip.cc.tcc.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="musical_genre")
public class MusicalGenre implements Comparable<MusicalGenre>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="musical_genre_id")
	private Long id;
	
	@Column(name="genre_name", nullable = false)
	private String name;

	public MusicalGenre() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MusicalGenre other = (MusicalGenre) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "MusicalGenre [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int compareTo(MusicalGenre o) {
		return this.name.compareTo(o.name);
	}
	
}
