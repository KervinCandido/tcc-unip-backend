package br.unip.cc.tcc.model;

import java.util.Objects;

public class Message {

	private String sender;
	private String receiver;
	private String content;
	
	public Message() {
		super();
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, receiver, sender);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return Objects.equals(content, other.content) && Objects.equals(receiver, other.receiver)
				&& Objects.equals(sender, other.sender);
	}

	@Override
	public String toString() {
		return "Message [sender=" + sender + ", receiver=" + receiver + ", content=" + content + "]";
	}
}
