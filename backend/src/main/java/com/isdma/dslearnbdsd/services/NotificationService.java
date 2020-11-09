package com.isdma.dslearnbdsd.services;



import java.time.Instant;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isdma.dslearnbdsd.dto.NotificationDTO;
import com.isdma.dslearnbdsd.entities.Deliver;
import com.isdma.dslearnbdsd.entities.Notification;
import com.isdma.dslearnbdsd.entities.User;
import com.isdma.dslearnbdsd.observers.DeliverRevisionObserver;
import com.isdma.dslearnbdsd.repositories.NotificationRepository;

@Service
public class NotificationService implements DeliverRevisionObserver{

	
	@Autowired
	private NotificationRepository repository;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private DeliverService deliverService;
	
	@PostConstruct //para garantir que é executado na hora certa, faz este metodo ser chamado logo apos o NotificationService ser instanciado
	private void initialize(){ //AQUI È QUE FAÇO INSCRIÇÂO LA NO DELIVERSERVICE
		deliverService.subscribeDeliverRevisionObserver(this); //o próprio se inscreve
	}
	
	@Transactional(readOnly = true)
	public Page<NotificationDTO> notificationsForCurrentUser(boolean unreadOnly, Pageable pageable){
		User user = authService.authenticated();
		
		Page<Notification> page = repository.find(user, unreadOnly, pageable);
		return page.map(x -> new NotificationDTO(x));
	}
	
	@Transactional
	public void saveDeliverNotification(Deliver deliver) { //recebemos delivery corrigida e geramos notificação para estudante que fez a tarefa
		
		Long sectionId = deliver.getLesson().getSection().getId();
		Long resourceId = deliver.getLesson().getSection().getResource().getId();
		Long offerId = deliver.getLesson().getSection().getResource().getOffer().getId();
		
		String route = "/offers/" + offerId + "/resources/" + resourceId + "/sections/" + sectionId;
		String text = deliver.getFeedback();
		Instant moment = Instant.now();
		User user = deliver.getEnrollment().getStudent();
		
		Notification notification = new Notification(null, text, moment, false, route, user);//read=false porque tamos a criar nao ta lida,
		
		repository.save(notification);
	}

	@Override
	public void onSaverevision(Deliver deliver) { //quando acontecer o evento de salvar um revisão eu quero salvar uma notificação
		saveDeliverNotification(deliver);
		
	}
}
