package com.fss.Digital.Banking.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fss.Digital.Banking.entites.Client;
import com.fss.Digital.Banking.entites.Role;
import com.fss.Digital.Banking.exceptions.ClientUnfoundException;
import com.fss.Digital.Banking.repositories.ClientRepository;
import com.fss.Digital.Banking.repositories.RoleRepository;

@Service

public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private RoleRepository roleRepository;
	//@Autowired
	//private PasswordEncoder passwordEncoder;

	@Override
	public Client EnregistrerClient(Client client) {
		// TODO Auto-generated method stub
		String password=client.getPassword();
	//	client.setPassword(passwordEncoder.encode(password));
		return clientRepository.save(client);
	}

	@Override
	public Client RechercheClient(Long id) throws ClientUnfoundException {
		// TODO Auto-generated method stub
		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new ClientUnfoundException("Client inexsistant"));

		return client;
	}

	@Override
	public void ModifierClient(Client client) throws ClientUnfoundException {
		// TODO Auto-generated method stub
		Client clt = clientRepository.findById(client.getId())
				.orElseThrow(() -> new ClientUnfoundException("Client inexistant"));
		clt.setNom(client.getNom());
		clt.setPrenom(client.getPrenom());
		clt.setEmail(client.getEmail());
		clientRepository.save(clt);
	}

	@Override
	public void SupprimerClient(Long id) throws ClientUnfoundException {
		// TODO Auto-generated method stub
		Client client = RechercheClient(id);
		if (client == null)
			throw new ClientUnfoundException("Client inexistant");
		clientRepository.deleteById(id);

	}

	@Override
	public List<Client> RechercherTousClients() {
		// TODO Auto-generated method stub
		List<Client> clients = clientRepository.findAll();
		return clients;
	}

	@Override
	public void ajouterRoleAutilisateur(String nomDuRole, String username) {
		// TODO Auto-generated method stub
		Client user = clientRepository.findByUsername(username);
		Role role = roleRepository.findByNomDuRole(nomDuRole);
        user.getRole().add(role); 
	}

	@Override
	public Client chargerUtilisateurParUsername(String username) {
		// TODO Auto-generated method stub
		return clientRepository.findByUsername(username);

	}

}
