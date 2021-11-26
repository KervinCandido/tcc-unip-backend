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
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "find_recommend_users")
public class RecommendedUser {

	@Id
	@Column(name="UUID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String uuid;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name = "recommended_username")
	private String userName;
	
	@Column(name="recommended_user_id")
	private Long recommendedUserId;
	
	@Column(name="recommended_profile_id")
	private Long profileId;
	
	@Column(name = "recommended_profile_name", nullable = false)
	private String profileName;
	
	@Column(name = "recommended_birth_date", nullable = false)
	private LocalDate birthDate;
	
	@Column(name = "recommended_gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(name = "recommended_photo")
	private String photo;
	
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(name = "recommended_description")
	private String description;

	public RecommendedUser() {
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getRecommendedUserId() {
		return recommendedUserId;
	}

	public void setRecommendedUserId(Long recommendedUserId) {
		this.recommendedUserId = recommendedUserId;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
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
	public int hashCode() {
		return Objects.hash(birthDate, gender, profileId, profileName, recommendedUserId, userId, userName, uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecommendedUser other = (RecommendedUser) obj;
		return Objects.equals(birthDate, other.birthDate) && gender == other.gender
				&& Objects.equals(profileId, other.profileId) && Objects.equals(profileName, other.profileName)
				&& Objects.equals(recommendedUserId, other.recommendedUserId) && Objects.equals(userId, other.userId)
				&& Objects.equals(userName, other.userName) && Objects.equals(uuid, other.uuid);
	}

	@Override
	public String toString() {
		return "RecommendedUser [uuid=" + uuid + ", userId=" + userId + ", userName=" + userName
				+ ", recommendedUserId=" + recommendedUserId + ", profileId=" + profileId + ", profileName="
				+ profileName + ", birthDate=" + birthDate + ", gender=" + gender + "]";
	}
	
}
