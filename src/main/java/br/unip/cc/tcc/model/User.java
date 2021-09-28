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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user_tcc")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "username", nullable = false)
	private UserOpenFire userOpenFire;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_permission", 
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), 
		inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "permission_id"))
	private List<Permission> permissions;
	
	@Column(name = "exclusion_date")
	private LocalDateTime exclusionDate;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permissions;
	}

	@Override
	public String getPassword() {
		return userOpenFire.getPassword();
	}

	@Override
	public String getUsername() {
		return userOpenFire.getUserName();
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

	public UserOpenFire getUserOpenFire() {
		return userOpenFire;
	}

	public void setUserOpenFire(UserOpenFire userOpenFire) {
		this.userOpenFire = userOpenFire;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", userOpenFire=" + userOpenFire + ", permissions=" + permissions + ", exclusionDate="
				+ exclusionDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(exclusionDate, id, permissions, userOpenFire);
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
		return Objects.equals(exclusionDate, other.exclusionDate) && Objects.equals(id, other.id)
				&& Objects.equals(permissions, other.permissions) && Objects.equals(userOpenFire, other.userOpenFire);
	}
}
