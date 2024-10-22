package com.fss.Digital.Banking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fss.Digital.Banking.entites.Role;
import com.fss.Digital.Banking.services.RoleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j

public class RoleController {
	@Autowired
	private RoleService RoleServiceImpl;

	/*
	 * il etait possible d'implementer la logique du methode AjoutRole dans la
	 * methode run au niveau la classe principale DigitalBankingApplication mais il
	 * illogique a chaque fois du demmarage de l'application on ajoute a chaque fois
	 * les memes roles alors que il doivent etre fixer c est pourqoui j implemente
	 * la logique de cette methode il suffit d'appeller une seule fois pour ajouter
	 * les roles en cas on veut ajouter un nouveau role on peut l'appeller bien sur
	 */
	@PostMapping("/role")
	public void AjoutRole(@RequestBody Role role) {
		RoleServiceImpl.ajouterRole(role);
		log.info("Enrigestrement d\'un nouveau role");

	}

}
