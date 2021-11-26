package br.unip.cc.tcc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.unip.cc.tcc.controller.dto.RequestContactDTO;
import br.unip.cc.tcc.controller.form.ConfirmationRequestContactForm;
import br.unip.cc.tcc.controller.form.RejectRequestContactForm;
import br.unip.cc.tcc.controller.form.RequestContactForm;
import br.unip.cc.tcc.model.RequestContact;
import br.unip.cc.tcc.repository.RequestContactRepository;
import br.unip.cc.tcc.repository.UserRepository;

@Service
public class RequestContactService {
	
	@Autowired
	private RequestContactRepository rcRepository;
	
	@Autowired
	private UserRepository userRepository;

	public Optional<RequestContactDTO> save(RequestContactForm requestAddContact) {
		var optionalFrom = userRepository.findByUserName(requestAddContact.getFrom());
		var optionalTo = userRepository.findByUserName(requestAddContact.getTo());
		
		if (optionalFrom.isEmpty() || optionalTo.isEmpty()) {
			System.err.println("Um dos usuários não foi encontrado !!!");
			return Optional.empty();
		}
		
		RequestContact rc = new RequestContact();
		rc.setRequester(optionalFrom.get());
		rc.setRequested(optionalTo.get());
		rc.setStatus(RequestContact.Status.PENDENTE);
		return Optional.of(new RequestContactDTO(rcRepository.save(rc)));
	}

	public void confirmation(ConfirmationRequestContactForm confirmation) {
		setStatusRequestContact(confirmation.getRequestId(), RequestContact.Status.CONFIRMADO);
	}

	public void reject(RejectRequestContactForm reject) {
		setStatusRequestContact(reject.getRequestId(), RequestContact.Status.REJEITADO);
	}
	
	private void setStatusRequestContact(Long id, RequestContact.Status status) {
		Optional<RequestContact> optional = rcRepository.findById(id);
		optional.ifPresent(rc -> {
			rc.setStatus(status);
			rcRepository.saveAndFlush(rc);
		});
	}

	public Page<RequestContactDTO> findRequests(Long userId, Pageable pageable) {
		return rcRepository.findByRequestedIdAndStatus(userId, RequestContact.Status.PENDENTE, pageable);
	}
}
