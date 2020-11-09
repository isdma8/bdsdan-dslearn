package com.isdma.dslearnbdsd.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.isdma.dslearnbdsd.entities.Notification;
import com.isdma.dslearnbdsd.entities.User;

public interface NotificationRepository extends JpaRepository<Notification, Long>{

	
	//Preciso de metodo personalizado para retornar busca paginada das notificações do user podendo ser as nao lidas ou todas
	//nao é bem sql é jpql, entao temos de colocar classe exatamente mesmo nome e dar nome que quisersmos no caso obj e no fim obj trambem
	@Query("SELECT obj FROM Notification obj WHERE " 
			+ "(obj.user = :user) AND " //com o :user tou a ir buscar o paramtro de entrada da função
			+ "(:unreadOnly = false OR obj.read = false) " //colocamos um OU, se a primeira parte for verdadeiro, ou seja vir false o unreadonly ele nem verificar a segunda parte entao vai buscar todas as notificações, senao vier true entao a primeira condição falha e coloca-a entao a segunda que faz aparecer a condição no sql e pedir apenas as que estao nao lidas
			+ "ORDER BY obj.moment DESC")
	Page<Notification> find(User user, boolean unreadOnly, Pageable pageable);
	
	//Page<Notification> findByUserAndReadOrderByMomentDesc(User user, boolean unreadOnly, Pageable pageable); //so com este nome o Spring JPA converteria para a consulta de cima mas nao podemos fazer por esta forma facilitada porque precisamos do macete do Read
	

}
