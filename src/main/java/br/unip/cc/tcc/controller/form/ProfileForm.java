package br.unip.cc.tcc.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import br.unip.cc.tcc.model.Gender;
import br.unip.cc.tcc.model.Profile;
import br.unip.cc.tcc.model.User;

public class ProfileForm {
	
	@Min(1)
	private Long userId;
	
	@NotEmpty
	@Size(min = 3, max = 64)
	private String profileName;
	
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull
	private LocalDate birthDate;
	
	@NotNull
	@Pattern(regexp = "^MALE$|^FEMALE$", message = "Gênero inválido")
	private String gender;
	
	private MultipartFile photo;
	private String description;
	
	private Long[] favoriteMusicalGenre;
	private Long[] favoriteMovieGenre;
	
	public ProfileForm () {}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public MultipartFile  getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile  photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long[] getFavoriteMusicalGenre() {
		return favoriteMusicalGenre != null ? favoriteMusicalGenre : new Long[0];
	}

	public void setFavoriteMusicalGenre(Long[] favoriteMusicalGenre) {
		this.favoriteMusicalGenre = favoriteMusicalGenre;
	}

	public Long[] getFavoriteMovieGenre() {
		return favoriteMovieGenre != null ? favoriteMovieGenre : new Long[0];
	}

	public void setFavoriteMovieGenre(Long[] favoriteMovieGenre) {
		this.favoriteMovieGenre = favoriteMovieGenre;
	}

	@Override
	public String toString() {
		return "ProfileForm [userId=" + userId + ", profileName=" + profileName + ", birthDate=" + birthDate
				+ ", gender=" + gender + ", " + "description=" + description + "]";
	}
	
	public Profile toProfile() {
		Profile profile = new Profile();
		User user = new User();
		user.setId(userId);
		
		profile.setUser(user);
		profile.setProfileName(profileName);
		profile.setBirthDate(birthDate);
		profile.setGender(Gender.valueOf(gender));
		profile.setDescription(description);
		
		return profile;
	}
}
