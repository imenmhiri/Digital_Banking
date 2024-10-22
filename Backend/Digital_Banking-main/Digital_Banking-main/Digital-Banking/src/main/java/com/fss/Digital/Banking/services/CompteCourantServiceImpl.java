package com.fss.Digital.Banking.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fss.Digital.Banking.entites.Client;
import com.fss.Digital.Banking.entites.CompteBancaire;
import com.fss.Digital.Banking.entites.CompteCourant;
import com.fss.Digital.Banking.enums.CompteStatus;
import com.fss.Digital.Banking.exceptions.ClientUnfoundException;
import com.fss.Digital.Banking.exceptions.CompteBancaireUnfoundException;
import com.fss.Digital.Banking.repositories.CompteBancaireRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CompteCourantServiceImpl implements CompteBancaireService {
	@Autowired
	private CompteBancaireRepository compteBancaireRepository;
	

	@Override
	public void SupprimerCompteBancaire(Long compteBancaireId) {
		// TODO Auto-generated method stub
		compteBancaireRepository.deleteById(compteBancaireId);

	}

	@Override
	public void EnregistrerCompteBancaire(double solde, Client client, double compteSpecific)
			throws ClientUnfoundException {
		// TODO Auto-generated method stub
		CompteCourant compteCourant = new CompteCourant();
		compteCourant.setClient(client);
		compteCourant.setDatedecreation(new Date());
		compteCourant.setSolde(solde);
		compteCourant.setDecouvert(compteSpecific);
		compteCourant.setEtat(CompteStatus.ACTIVE);
		compteBancaireRepository.save(compteCourant);
		log.info("Enrigestrement d\'un nouveau compte bancaire courant");


	}

	@Override
	public void ModifierCompteBancaire(CompteBancaire compteBancaire, Double nouveauSolde,
			Double nouveauCompteSpecific) {
		// TODO Auto-generated method stub
		CompteCourant compteCourant = (CompteCourant) compteBancaire;
		if (nouveauSolde != null) {
			compteCourant.setSolde(nouveauSolde);
		}
		if (nouveauCompteSpecific != null) {
			compteCourant.setDecouvert(nouveauCompteSpecific);
		}
		compteBancaireRepository.save(compteCourant);
	}

	@Override
	public CompteBancaire ConsulterCompteBancaire(CompteBancaire compteBancaire) throws CompteBancaireUnfoundException {
		// TODO Auto-generated method stub
		CompteCourant compteCourant = (CompteCourant) compteBancaire;
		return compteCourant;
	}
}
