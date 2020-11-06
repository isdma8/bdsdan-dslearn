package com.isdma.dslearnbdsd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isdma.dslearnbdsd.entities.Topic;

public interface UserRepository extends JpaRepository<Topic, Long>{

}
