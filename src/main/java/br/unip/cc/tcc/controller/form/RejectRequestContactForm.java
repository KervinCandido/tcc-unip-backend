package br.unip.cc.tcc.controller.form;

import java.util.Objects;

public class RejectRequestContactForm {
	private Long requestId;
	private String from;
	private String to;
	
	public Long getRequestId() {
		return requestId;
	}
	
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getTo() {
		return to;
	}
	
	public void setTo(String to) {
		this.to = to;
	}

	@Override
	public int hashCode() {
		return Objects.hash(from, requestId, to);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RejectRequestContactForm other = (RejectRequestContactForm) obj;
		return Objects.equals(from, other.from) && Objects.equals(requestId, other.requestId)
				&& Objects.equals(to, other.to);
	}

	@Override
	public String toString() {
		return "RejectRequestContactForm [requestId=" + requestId + ", from=" + from + ", to=" + to + "]";
	}
	
}
