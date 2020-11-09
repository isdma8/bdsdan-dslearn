package com.isdma.dslearnbdsd.services;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isdma.dslearnbdsd.dto.DeliverRevisionDTO;
import com.isdma.dslearnbdsd.entities.Deliver;
import com.isdma.dslearnbdsd.observers.DeliverRevisionObserver;
import com.isdma.dslearnbdsd.repositories.DeliverRepository;

@Service
public class DeliverService {

	@Autowired
	private DeliverRepository repository;
	
	//@Autowired
	//private NotificationService notificationService; //dava mas não é bom conhecer, MLEHOR USER OBSERVERS(eventos) quando fizer a correção neste caso aqui então avisamos que o fizemos e então quem precisar no caso o notification que faça o que tiver a fazer
	
	private Set<DeliverRevisionObserver> deliverRevisionObservers = new LinkedHashSet<>(); //Linked mantem a ordem
	
	
	//salvar uma correção do professor //no fundo é um update do id do deliver que queremos alterar os dados
	@PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")    //depois de configurao no WebSecurityConfig, e aqui com isto so permitimos quem definirmos como admin neste caso e instructor
	@Transactional
	public void saveRevision(Long id, DeliverRevisionDTO dto) {
		Deliver deliver = repository.getOne(id);
		deliver.setStatus(dto.getStatus());
		deliver.setFeedback(dto.getFeedback());
		deliver.setCorrectCount(dto.getCorrectCount());
		
		repository.save(deliver);
		
		//Agora mesmo sem conhecer quem me estava observado eu executo para todos eles o metodo que sei que terão de implementar porque está na interface deles que no fundo eles mesmo sao depois de a implementarem, sao observers
		for(DeliverRevisionObserver observer : deliverRevisionObservers) {
			observer.onSaverevision(deliver);
		}
		
		//notificationService.saveDeliverNotification(deliver);
	}
	
	public void subscribeDeliverRevisionObserver(DeliverRevisionObserver observer) { //aberto a inscrições de quem precisar se inscrever nesta lista 
		deliverRevisionObservers.add(observer);
	}
}
