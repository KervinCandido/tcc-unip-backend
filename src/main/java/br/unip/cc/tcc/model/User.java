package br.unip.cc.tcc.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user_tcc")
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1144311867009784310L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	@Column(name = "username", nullable = false)
	private String userName;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "creation_date", nullable = false)
	private LocalDateTime creationDate;

	@Column(name = "modification_date", nullable = false)
	private LocalDateTime modificationDate;
	
	@Column(name = "exclusion_date")
	private LocalDateTime exclusionDate;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_permission", 
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), 
		inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "permission_id"))
	private List<Permission> permissions;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Contact> contacts;
	
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	private Profile profile;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permissions;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public LocalDateTime getExclusionDate() {
		return exclusionDate;
	}

	public void setExclusionDate(LocalDateTime exclusionDate) {
		this.exclusionDate = exclusionDate;
	}
	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(LocalDateTime modificationDate) {
		this.modificationDate = modificationDate;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creationDate, email, exclusionDate, id, modificationDate, name, password, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(creationDate, other.creationDate) && Objects.equals(email, other.email)
				&& Objects.equals(exclusionDate, other.exclusionDate) && Objects.equals(id, other.id)
				&& Objects.equals(modificationDate, other.modificationDate) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", name=" + name + ", email=" + email + ", creationDate="
				+ creationDate + ", modificationDate=" + modificationDate + ", exclusionDate=" + exclusionDate + "]";
	}
	
}
