package com.fss.Digital.Banking.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fss.Digital.Banking.entites.CompteBancaire;
import com.fss.Digital.Banking.entites.CompteEpargne;
import com.fss.Digital.Banking.enums.CompteStatus;
import com.fss.Digital.Banking.exceptions.ClientUnfoundException;
import com.fss.Digital.Banking.exceptions.CompteBancaireUnfoundException;
import com.fss.Digital.Banking.repositories.CompteBancaireRepository;

import jakarta.transaction.Transactional;

import com.fss.Digital.Banking.entites.Client;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CompteEpargneServiceImpl implements CompteBancaireService {
	@Autowired
	private CompteBancaireRepository compteBancaireRepository;
	
	@Override
	public CompteBancaire ConsulterCompteBancaire(CompteBancaire compteBancaire) throws CompteBancaireUnfoundException {
		CompteEpargne compteEpargne = (CompteEpargne) compteBancaire;
		return compteEpargne;
	}

	@Override
	public void SupprimerCompteBancaire(Long compteBancaireId) {
		// TODO Auto-generated method stub
		compteBancaireRepository.deleteById(compteBancaireId);
	}

	@Override
	public void EnregistrerCompteBancaire(double solde, Client client, double compteSpecific)
			throws ClientUnfoundException {
		// TODO Auto-generated method stub
		log.info("Enrigestrement d\'un nouveau compte bancaire epargne");
		CompteEpargne compteEpargne = new CompteEpargne();
		compteEpargne.setDatedecreation(new Date());
		compteEpargne.setClient(client);
		compteEpargne.setSolde(solde);
		compteEpargne.setTauxInteret(compteSpecific);
		compteEpargne.setEtat(CompteStatus.ACTIVE);
		compteBancaireRepository.save(compteEpargne);

	}

	@Override
	public void ModifierCompteBancaire(CompteBancaire compteBancaire, Double nouveauSolde,
			Double nouveauCompteSpecific) {
		// TODO Auto-generated method stub
		CompteEpargne compteEpargne = (CompteEpargne) compteBancaire;
		 if (nouveauSolde != null) {
			 compteEpargne.setSolde(nouveauSolde);
         }
         if (nouveauCompteSpecific != null) {
        	 compteEpargne.setTauxInteret(nouveauCompteSpecific);
         }
		compteBancaireRepository.save(compteEpargne);

	}
}
