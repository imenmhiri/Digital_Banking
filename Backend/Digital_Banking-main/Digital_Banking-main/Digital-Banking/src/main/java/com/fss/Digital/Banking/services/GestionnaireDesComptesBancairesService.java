package com.fss.Digital.Banking.services;

import java.util.List;

import com.fss.Digital.Banking.entites.CompteBancaire;
import com.fss.Digital.Banking.exceptions.ClientUnfoundException;
import com.fss.Digital.Banking.exceptions.CompteBancaireUnfoundException;

public interface GestionnaireDesComptesBancairesService {

	CompteBancaire RechercheCompteBancaire(Long CompteBancaireid) throws CompteBancaireUnfoundException;

	void ModifierClient(Long compteBancaireId, Double nouveauSolde, Double nouveauCompteSpecific) throws CompteBancaireUnfoundException;

	void SupprimerCompteBancaire(Long CompteBancaireid) throws CompteBancaireUnfoundException;

	List<CompteBancaire> RechercheTousComptesBancaire();

	void EnregistrerCompteBancaire(double solde, Long clientId, double compteSpecific, String type)
			throws ClientUnfoundException, CompteBancaireUnfoundException;

}
