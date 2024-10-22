package com.fss.Digital.Banking.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fss.Digital.Banking.entites.CompteBancaire;
import com.fss.Digital.Banking.entites.Operation;
import com.fss.Digital.Banking.enums.TypeOperation;
import com.fss.Digital.Banking.exceptions.CompteBancaireUnfoundException;
import com.fss.Digital.Banking.exceptions.SoldeInsuffisantException;
import com.fss.Digital.Banking.repositories.CompteBancaireRepository;
import com.fss.Digital.Banking.repositories.OperationRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class OperationServiceImpl implements OperationService {
	@Autowired
	private CompteBancaireRepository compteBancaireRepository;
	
	@Autowired
	private OperationRepository operationRepository;

	@Override
	public void crediter(Long id, double montant) throws CompteBancaireUnfoundException {
		// TODO Auto-generated method stub
		CompteBancaire compteBancaire = compteBancaireRepository.findById(id)
				.orElseThrow(() -> new CompteBancaireUnfoundException("CompteBancaire inexsistant"));
		Operation operation = new Operation();
		operation.setDateOperation(new Date());
		operation.setType(TypeOperation.CREDIT);
		operation.setMontant(montant);
		operation.setCompteBancaire(compteBancaire);
		operationRepository.save(operation);
		compteBancaire.setSolde(compteBancaire.getSolde() + montant);
		compteBancaireRepository.save(compteBancaire);
	}

	@Override
	public void debiter(Long id, double montant) throws CompteBancaireUnfoundException, SoldeInsuffisantException {
		// TODO Auto-generated method stub
		CompteBancaire compteBancaire = compteBancaireRepository.findById(id)
				.orElseThrow(() -> new CompteBancaireUnfoundException("CompteBancaire inexsistant"));
		if ((compteBancaire.getSolde() < montant) || (compteBancaire.getSolde() < 0))
			throw new SoldeInsuffisantException("Solde Insuffisant");
		Operation operation = new Operation();
		operation.setDateOperation(new Date());
		operation.setType(TypeOperation.DEBIT);
		operation.setMontant(montant);
		operation.setCompteBancaire(compteBancaire);
		operationRepository.save(operation); 
		compteBancaire.setSolde(compteBancaire.getSolde()-montant);
		compteBancaireRepository.save(compteBancaire); 
	}

	@Override
	public void virement(Long idCompteSource, long idCompteDestination, double montant)
			throws CompteBancaireUnfoundException, SoldeInsuffisantException {
		// TODO Auto-generated method stub
		 debiter(idCompteSource, montant); 
		 crediter(idCompteDestination,  montant); 

	}

	@Override
	public Operation ConsulterCompteOperation(Long compteBancaireId) {
		// TODO Auto-generated method stub
		return null;
	}

}
