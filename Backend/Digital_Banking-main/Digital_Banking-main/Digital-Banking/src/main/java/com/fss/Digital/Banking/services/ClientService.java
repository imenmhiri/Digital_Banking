package com.fss.Digital.Banking.services;

import java.util.List;

import com.fss.Digital.Banking.entites.Client;
import com.fss.Digital.Banking.exceptions.ClientUnfoundException;

public interface ClientService {
	Client EnregistrerClient(Client client);

	Client RechercheClient(Long id) throws ClientUnfoundException;

	List<Client> RechercherTousClients();

	void ModifierClient(Client client) throws ClientUnfoundException;

	void SupprimerClient(Long id) throws ClientUnfoundException;

	void ajouterRoleAutilisateur(String nomDuRole, String username);
	
	Client chargerUtilisateurParUsername (String username); 
}
