package com.fss.Digital.Banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fss.Digital.Banking.entites.Client;


public interface ClientRepository extends JpaRepository<Client, Long> {
	public Client findByUsername(String username);

}
