package com.isdma.dslearnbdsd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isdma.dslearnbdsd.dto.DeliverRevisionDTO;
import com.isdma.dslearnbdsd.entities.Deliver;
import com.isdma.dslearnbdsd.repositories.DeliverRepository;

@Service
public class DeliverService {

	@Autowired
	private DeliverRepository repository;
	
	//salvar uma correção do professor //no fundo é um update do id do deliver que queremos alterar os dados
	@PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")    //depois de configurao no WebSecurityConfig, e aqui com isto so permitimos quem definirmos como admin neste caso e instructor
	@Transactional
	public void saveRevision(Long id, DeliverRevisionDTO dto) {
		Deliver deliver = repository.getOne(id);
		deliver.setStatus(dto.getStatus());
		deliver.setFeedback(dto.getFeedback());
		deliver.setCorrectCount(dto.getCorrectCount());
		
		repository.save(deliver);
	}
}
