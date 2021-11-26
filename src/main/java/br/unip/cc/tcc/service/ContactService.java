package br.unip.cc.tcc.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.unip.cc.tcc.controller.dto.ContactDTO;
import br.unip.cc.tcc.controller.form.ConfirmationRequestContactForm;
import br.unip.cc.tcc.model.Contact;
import br.unip.cc.tcc.model.Profile;
import br.unip.cc.tcc.model.RecommendedUser;
import br.unip.cc.tcc.model.User;
import br.unip.cc.tcc.repository.ContactRepository;
import br.unip.cc.tcc.repository.ProfileRepository;
import br.unip.cc.tcc.repository.RecommendedUserRepository;
import br.unip.cc.tcc.repository.UserRepository;

@Service
public class ContactService {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RecommendedUserRepository recommendedUser;
	
	public Page<ContactDTO> findRecommendation(Long userId, Pageable pageable) {
		Page<RecommendedUser> recommendedUsers = recommendedUser.findByUserId(userId, pageable);
		return recommendedUsers.map(ContactDTO::new);
	}
	
	public Optional<ContactDTO> findByUserName(String userName) {
		Optional<Profile> optional = profileRepository.findByUserUserName(userName);
		return optional.map(ContactDTO::new);
	}

	@Transactional
	public void addToContactList(ConfirmationRequestContactForm confirmation) {
		Optional<User> optionalUserFrom = userRepository.findByUserName(confirmation.getFrom());
		Optional<User> optionalUserTo = userRepository.findByUserName(confirmation.getTo());
		
		if (optionalUserFrom.isEmpty() || optionalUserTo.isEmpty()) return;
		
		User userFrom = optionalUserFrom.get();
		User userTo = optionalUserTo.get();
		
		Contact contactTo = new Contact();
		contactTo.setUser(userTo);
		contactTo.setUserContact(userFrom);
		
		Contact contactFrom = new Contact();
		contactFrom.setUser(userFrom);
		contactFrom.setUserContact(userTo);
		
		contactRepository.save(contactTo);
		contactRepository.save(contactFrom);
		
	}

	public Page<ContactDTO> findContacts(Long userId, Pageable pageable) {
		Page<Contact> contacts = contactRepository.findByUserId(userId, pageable);
		return contacts.map(contact -> new ContactDTO(contact.getUserContact().getProfile()));
	}
}
