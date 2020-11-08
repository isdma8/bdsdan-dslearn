package com.isdma.dslearnbdsd.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isdma.dslearnbdsd.dto.NotificationDTO;
import com.isdma.dslearnbdsd.services.NotificationService;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationResource {

	@Autowired
	private NotificationService service;
	
	@GetMapping
	public ResponseEntity<Page<NotificationDTO>> findAll(
			@RequestParam(value = "unreadOnly", defaultValue = "false") Boolean unreadOnly, //pedir so as nao lidas
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "12") Integer size
			){
		
		//Declarar objeto especial do Spring e instancio com um metodo de builder delel com os parametros que queremos definidos acima
		PageRequest pagerequest = PageRequest.of(page, size);		
		
		Page<NotificationDTO> list = service.notificationsForCurrentUser(unreadOnly, pagerequest);
		
		return ResponseEntity.ok(list);
		
	}
}
