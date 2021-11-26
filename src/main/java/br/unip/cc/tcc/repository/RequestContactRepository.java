package br.unip.cc.tcc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.cc.tcc.controller.dto.RequestContactDTO;
import br.unip.cc.tcc.model.RequestContact;
import br.unip.cc.tcc.model.RequestContact.Status;

public interface RequestContactRepository extends JpaRepository<RequestContact, Long> {
	Page<RequestContactDTO> findByRequestedIdAndStatus(Long userId, Status pendente, Pageable pageable);

}
