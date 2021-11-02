package br.unip.cc.tcc.controller.dto;

import br.unip.cc.tcc.model.RequestContact;

public record RequestContactDTO(Long requestId, ContactDTO from, ContactDTO to) {
	public RequestContactDTO(RequestContact rc) {
		this(rc.getId(), new ContactDTO(rc.getRequester().getProfile()), new ContactDTO(rc.getRequested().getProfile()));
	}
}
