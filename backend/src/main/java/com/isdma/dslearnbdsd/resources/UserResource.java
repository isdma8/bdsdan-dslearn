package com.isdma.dslearnbdsd.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isdma.dslearnbdsd.dto.UserDTO;
import com.isdma.dslearnbdsd.services.UserService;

//O nosso recourse implementa o controller Rest, é a nossa API(Application programming interface) do nosso backend
//sao os recursos disponibilizados para os  frontoffice
//Neste caso esta classe é o recurso da entidade User

@RestController // Annotation para o Spring saber que é uma class de resources, forma de
				// implementar algo que ja foi implementado
@RequestMapping(value = "/users") // rota REST do nosso recurso
public class UserResource {

	@Autowired // Para injetar automaticamente as dependencias
	private UserService service;
	

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {

		UserDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);

	}

}
