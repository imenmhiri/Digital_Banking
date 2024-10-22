package com.fss.Digital.Banking.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import com.fss.Digital.Banking.entites.Client;
import com.fss.Digital.Banking.exceptions.ClientUnfoundException;
import com.fss.Digital.Banking.services.ClientService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
//Sl4j pour la journalisation des messages 
@CrossOrigin("*")
public class ClientRestController {
	@Autowired
	private ClientService ClientServiceImpl;

	@PostMapping("/clients")
	//@PostAuthorize("hasAuthority('ADMIN')")
	public void SaveClient(@RequestBody Client client) {
		ClientServiceImpl.EnregistrerClient(client);
		log.info("Enrigestrement d\'un nouveau client");

	}

	@GetMapping("/clients")
	//@PostAuthorize("hasAuthority('ADMIN')")
	public List<Client> ConsulterTousClient() {
		return ClientServiceImpl.RechercherTousClients();
	}

	@GetMapping("/clients/{clientId}")
	//@PostAuthorize("hasAuthority('USER')")
	public Client ConsulterUnClient(@PathVariable Long clientId) throws ClientUnfoundException {
		return ClientServiceImpl.RechercheClient(clientId);
	}

	/*
	 * @PutMapping("/clients") public void ModifierUnClient(@RequestBody Client
	 * client) throws ClientUnfoundException{
	 * ClientServiceImpl.ModifierClient(client) ; }
	 */
	@PutMapping("/clients/{clientId}")
	//@PostAuthorize("hasAuthority('ADMIN')")
	public void ModifierUnClient(@PathVariable Long clientId, @RequestBody Client client)
			throws ClientUnfoundException {
		client.setId(clientId);
		ClientServiceImpl.ModifierClient(client);
		log.info("Modification du client effectuée");

	}
	@PostMapping("/clients/role")
	public void AffecterRoleAunUtilisateur(@RequestParam String nomDuRole,@RequestParam String username ) {
		ClientServiceImpl.ajouterRoleAutilisateur(nomDuRole,username );

	}

	@DeleteMapping("/clients/{clientId}")
	//@PostAuthorize("hasAuthority('ADMIN')")
	public void DeleteClient(@PathVariable Long clientId) throws ClientUnfoundException {
		ClientServiceImpl.SupprimerClient(clientId);
		log.info("Suppression du client effectuée");

	}

}
