package com.isdma.dslearnbdsd.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isdma.dslearnbdsd.entities.User;
import com.isdma.dslearnbdsd.repositories.UserRepository;
import com.isdma.dslearnbdsd.services.exceptions.ForbiddenException;
import com.isdma.dslearnbdsd.services.exceptions.UnauthorizedException;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public User authenticated() { //que user está autenticado?
		
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName(); //pega nome do user que foi reconecido pelo spring security
	
			return userRepository.findByEmail(username);
		}
		catch(Exception e) {
			throw new UnauthorizedException("Invalid User");
		}
		
	}
	
	
	public void validateSelfOrAdmin(Long userId) { //se for o próprio user  pesquisar por si ou um admin passa senao nao passa
		User user = authenticated();
		if(!user.getId().equals(userId) && !user.hasHole("ROLE_ADMIN")) {
			throw new ForbiddenException("Access denied");
		}
	}

}
