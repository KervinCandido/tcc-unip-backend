package br.unip.cc.tcc.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "request_contact")
public class RequestContact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_contact_id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_requester", referencedColumnName = "user_id", nullable = false)
	private User requester;
	
	@OneToOne
	@JoinColumn(name = "user_requested", referencedColumnName = "user_id", nullable = false)
	private User requested;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getRequester() {
		return requester;
	}

	public void setRequester(User requester) {
		this.requester = requester;
	}

	public User getRequested() {
		return requested;
	}

	public void setRequested(User requested) {
		this.requested = requested;
	}

	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, requested, requester, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestContact other = (RequestContact) obj;
		return Objects.equals(id, other.id) && Objects.equals(requested, other.requested)
				&& Objects.equals(requester, other.requester) && status == other.status;
	}

	@Override
	public String toString() {
		return "RequestContact [id=" + id + ", requester=" + requester + ", requested=" + requested + ", status="
				+ status + "]";
	}

	public enum Status {
		PENDENTE, CONFIRMADO, REJEITADO
	}
}
