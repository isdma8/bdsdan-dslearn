package com.isdma.dslearnbdsd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isdma.dslearnbdsd.entities.Topic;
import com.isdma.dslearnbdsd.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	
	User findByEmail(String email);
}
