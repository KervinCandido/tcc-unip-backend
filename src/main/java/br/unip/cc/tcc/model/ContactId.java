package br.unip.cc.tcc.model;

import java.io.Serializable;
import java.util.Objects;

public class ContactId implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6024382327484733061L;
	private Long user;
	private Long userContact;
	
	public Long getUser() {
		return user;
	}
	
	public void setUser(Long user) {
		this.user = user;
	}
	
	public Long getUserContact() {
		return userContact;
	}
	
	public void setUserContact(Long userContact) {
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
		ContactId other = (ContactId) obj;
		return Objects.equals(user, other.user) && Objects.equals(userContact, other.userContact);
	}

}
