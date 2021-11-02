package br.unip.cc.tcc.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
@IdClass(ContactId.class)
public class Contact {
	
	@Id
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
	private User user;
	
	@Id
	@OneToOne
	@JoinColumn(name = "user_id_contact", referencedColumnName = "user_id", nullable = false)
	private User userContact;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUserContact() {
		return userContact;
	}

	public void setUserContact(User userContact) {
		this.userContact = userContact;
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, userContact);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(user, other.user) && Objects.equals(userContact, other.userContact);
	}
}
