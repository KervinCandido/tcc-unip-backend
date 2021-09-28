package br.unip.cc.tcc.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="profile_id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(name = "profile_name", nullable = false)
	private String profileName;
	
	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;
	
	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(name = "photo")
	private String photo;
	
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(name = "description")
	private String description;
	
	public Profile() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Profile [user=" + user + ", profileName=" + profileName + ", birthDate=" + birthDate + ", gender="
				+ gender + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, description, gender, id, photo, profileName, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(description, other.description)
				&& gender == other.gender && Objects.equals(id, other.id) && Objects.equals(photo, other.photo)
				&& Objects.equals(profileName, other.profileName) && Objects.equals(user, other.user);
	}
}
