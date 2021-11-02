package br.unip.cc.tcc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.cc.tcc.model.Contact;
import br.unip.cc.tcc.model.ContactId;

public interface ContactRepository extends JpaRepository<Contact, ContactId> {

	Page<Contact> findByUserId(Long userId, Pageable pageable);
	
}
