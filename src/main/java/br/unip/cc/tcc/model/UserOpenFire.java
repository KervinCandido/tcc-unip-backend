package br.unip.cc.tcc.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.unip.cc.tcc.model.jpa.converter.LocalDateTimeOpenFireConverter;

@Entity
@Table(name = "ofuser")
public class UserOpenFire {

	@Id
	@Column(name = "username", nullable = false)
	private String userName;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "encryptedpassword")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "creationdate", nullable = false)
	@Convert(converter = LocalDateTimeOpenFireConverter.class)
	private LocalDateTime creationDate;

	@Column(name = "modificationdate", nullable = false)
	@Convert(converter = LocalDateTimeOpenFireConverter.class)
	private LocalDateTime modificationDat;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public LocalDateTime getModificationDat() {
		return modificationDat;
	}

	public void setModificationDat(LocalDateTime modificationDat) {
		this.modificationDat = modificationDat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserOpenFire [userName=" + userName + ", name=" + name + ", email=" + email + ", creationDate="
				+ creationDate + ", modificationDat=" + modificationDat + "]";
	}
}
