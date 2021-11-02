package br.unip.cc.tcc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unip.cc.tcc.controller.dto.ContactDTO;
import br.unip.cc.tcc.controller.dto.RequestContactDTO;
import br.unip.cc.tcc.controller.form.ConfirmationRequestContactForm;
import br.unip.cc.tcc.controller.form.RejectRequestContactForm;
import br.unip.cc.tcc.controller.form.RequestContactForm;
import br.unip.cc.tcc.service.ContactService;
import br.unip.cc.tcc.service.RequestContactService;

@RestController
@RequestMapping("/contact")
@MessageMapping("/contact")
public class ContactController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private ContactService contactService;
	
	@Autowired
	private RequestContactService rcService;

	@MessageMapping("/request")
	public void request(@Payload RequestContactForm requestAddContact) {
		Optional<RequestContactDTO> optionalRequestContactDTO = rcService.save(requestAddContact);
		
		optionalRequestContactDTO.ifPresent(rcDTO -> {
			simpMessagingTemplate.convertAndSend(
					"/user/" + requestAddContact.getTo() + "/contact/request/queue", 
					rcDTO);		
		});
	}

	@MessageMapping("/confirmation")
	public void confirmation(@Payload ConfirmationRequestContactForm confirmation) {
		contactService.addToContactList(confirmation);
		var contactOptional = contactService.findByUserName(confirmation.getFrom());
		
		rcService.confirmation(confirmation);
		
		contactOptional.ifPresent(contact -> {
			simpMessagingTemplate.convertAndSend(
					"/user/" + confirmation.getTo() + "/contact/confirmation/queue",
					contact);			
		});
	}

	@MessageMapping("/reject")
	public void rejectRequest(@Payload RejectRequestContactForm reject) {
		var contactOptional = contactService.findByUserName(reject.getFrom());
		rcService.reject(reject);
		
		contactOptional.ifPresent(contact -> {
		simpMessagingTemplate.convertAndSend(
				"/user/" + reject.getTo() + "/contact/reject/queue",
				contact);
		});
	}

	@GetMapping("/recommendations/{id}")
	public Page<ContactDTO> recommendations(@PathVariable("id") Long userId,
			@PageableDefault(page = 0, size = 20, direction = Direction.DESC) Pageable pageable) {
		return contactService.findRecommendation(userId, pageable);
	}
	
	@GetMapping("/{id}")
	public Page<ContactDTO> getContacts(@PathVariable("id") Long userId,
			@PageableDefault(page = 0, size = 20, direction = Direction.DESC) Pageable pageable) {
		return contactService.findContacts(userId, pageable);
	}
}
