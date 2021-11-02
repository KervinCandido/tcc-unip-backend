package br.unip.cc.tcc.controller;

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
import br.unip.cc.tcc.controller.form.RequestAddContactForm;
import br.unip.cc.tcc.service.ContactService;

@RestController
@RequestMapping("/contact")
@MessageMapping("/contact")
public class ContactController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private ContactService contactService;

	@MessageMapping("/request")
	public void request(@Payload RequestAddContactForm requestAddContact) {
		System.out.println("REQUEST ADD CONTACT: "+ requestAddContact);
		var contactOptional = contactService.findByUserName(requestAddContact.getFrom());
		
		contactOptional.ifPresent(contact -> {
			System.out.println("/user/" + requestAddContact.getTo() + "/contact/request/queue");
			
			simpMessagingTemplate.convertAndSend(
					"/user/" + requestAddContact.getTo() + "/contact/request/queue", 
					contact);			
		});
	}

	@MessageMapping("/confirmation")
	public void confirmation(@Payload RequestAddContactForm confirmation) {
		contactService.addToContactList(confirmation);
		var contactOptional = contactService.findByUserName(confirmation.getFrom());
		
		contactOptional.ifPresent(contact -> {
			System.out.println("/user/" + confirmation.getTo() + "/contact/confirmation/queue");
			
			simpMessagingTemplate.convertAndSend(
					"/user/" + confirmation.getTo() + "/contact/confirmation/queue", 
					contact);			
		});
	}

	@MessageMapping("/reject")
	public void rejectRequest(@Payload RequestAddContactForm reject) {
		System.out.println("reject: "+ reject);
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
