package br.unip.cc.tcc.config.validation.exception;

public class FormErrorException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3257686977847159559L;
	private String field;
	private String message;

	public FormErrorException(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
