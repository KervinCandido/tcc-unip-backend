package br.unip.cc.tcc.controller.dto;

public record TokenDTO(String type, String token) {
	public static final String TYPE_BEARER = "Bearer";
}
