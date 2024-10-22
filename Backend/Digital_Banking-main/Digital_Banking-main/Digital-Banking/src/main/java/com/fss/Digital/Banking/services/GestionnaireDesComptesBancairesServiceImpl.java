package com.fss.Digital.Banking.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fss.Digital.Banking.entites.Client;
import com.fss.Digital.Banking.entites.CompteBancaire;
import com.fss.Digital.Banking.entites.CompteCourant;
import com.fss.Digital.Banking.entites.CompteEpargne;
import com.fss.Digital.Banking.exceptions.ClientUnfoundException;
import com.fss.Digital.Banking.exceptions.CompteBancaireUnfoundException;
import com.fss.Digital.Banking.repositories.ClientRepository;
import com.fss.Digital.Banking.repositories.CompteBancaireRepository;


@Service
public class GestionnaireDesComptesBancairesServiceImpl implements GestionnaireDesComptesBancairesService {

	@Autowired
	private CompteBancaireRepository compteBancaireRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteBancaireService compteEpargneServiceImpl;
	@Autowired
	private CompteBancaireService compteCourantServiceImpl;

	@Override
	public CompteBancaire RechercheCompteBancaire(Long CompteBancaireId) throws CompteBancaireUnfoundException {
		// TODO Auto-generated method stub
		CompteBancaire compteBancaire = compteBancaireRepository.findById(CompteBancaireId)
				.orElseThrow(() -> new CompteBancaireUnfoundException("CompteBancaire inexsistant")); 
		if (compteBancaire instanceof CompteEpargne) {
			CompteBancaire compteEpargne = compteEpargneServiceImpl.ConsulterCompteBancaire(compteBancaire);
			return  compteEpargne; 
		} else {
			CompteBancaire compteCourant = compteCourantServiceImpl.ConsulterCompteBancaire(compteBancaire);
			return compteCourant;
		}


	}

	@Override
	public void SupprimerCompteBancaire(Long CompteBancaireId) throws CompteBancaireUnfoundException {
		// TODO Auto-generated method stub
		CompteBancaire compteBancaire = compteBancaireRepository.findById(CompteBancaireId)
				.orElseThrow(() -> new CompteBancaireUnfoundException("CompteBancaire inexsistant")); 
		if (compteBancaire instanceof CompteEpargne) {
		     compteEpargneServiceImpl.SupprimerCompteBancaire(CompteBancaireId);
			//return  compteEpargne; 
		} else {
			compteCourantServiceImpl.SupprimerCompteBancaire(CompteBancaireId);
			//return compteCourant;
		}
	}

	@Override
	public List<CompteBancaire> RechercheTousComptesBancaire() {
		// TODO Auto-generated method stub
		List<CompteBancaire> comptesBancaires = compteBancaireRepository.findAll();
		comptesBancaires.stream().map(compteBancaire -> {
			if (compteBancaire instanceof CompteEpargne) {
				CompteEpargne compteEpargne = (CompteEpargne) compteBancaire;
				return compteEpargne;
			} else {
				CompteCourant compteCourant = (CompteCourant) compteBancaire;
				return compteCourant;
			}
		}).collect(Collectors.toList());
		return comptesBancaires;
	}

	@Override
	public void EnregistrerCompteBancaire(double solde, Long clientId, double compteSpecific, String type)
			throws ClientUnfoundException, CompteBancaireUnfoundException {
		// TODO Auto-generated method stub
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new ClientUnfoundException(" client inexsistant"));
		if (type.equals("Epargne")) {
			compteEpargneServiceImpl.EnregistrerCompteBancaire(solde, client, compteSpecific);

		} else if (type.equals("Courant")){
			compteCourantServiceImpl.EnregistrerCompteBancaire(solde, client, compteSpecific);
		}
		else {
			throw new CompteBancaireUnfoundException("Compte bancaire inexistant ou type erroné"); 
		}
	}
	@Override
	public void ModifierClient(Long compteBancaireId, Double nouveauSolde, Double nouveauCompteSpecific) throws CompteBancaireUnfoundException {
		// TODO Auto-generated method stub
		CompteBancaire compteBancaire = compteBancaireRepository.findById(compteBancaireId)
				.orElseThrow(() -> new CompteBancaireUnfoundException(" CompteBancaire inexsistant"));
		if (compteBancaire instanceof CompteEpargne) {
			
			compteEpargneServiceImpl.ModifierCompteBancaire(compteBancaire, nouveauSolde, nouveauCompteSpecific);

		} else {
			compteCourantServiceImpl.ModifierCompteBancaire(compteBancaire, nouveauSolde, nouveauCompteSpecific);

		}
	}
}
