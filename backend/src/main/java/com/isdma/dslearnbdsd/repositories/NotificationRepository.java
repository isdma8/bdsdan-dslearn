package com.isdma.dslearnbdsd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isdma.dslearnbdsd.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{

}
