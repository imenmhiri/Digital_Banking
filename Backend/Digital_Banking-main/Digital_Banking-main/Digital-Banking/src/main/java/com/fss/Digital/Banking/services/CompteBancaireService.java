package com.fss.Digital.Banking.services;

import com.fss.Digital.Banking.entites.Client;
import com.fss.Digital.Banking.entites.CompteBancaire;
import com.fss.Digital.Banking.exceptions.ClientUnfoundException;
import com.fss.Digital.Banking.exceptions.CompteBancaireUnfoundException;

public interface CompteBancaireService {
	void EnregistrerCompteBancaire(double solde, Client client, double compteSpecific) throws ClientUnfoundException;

	void ModifierCompteBancaire(CompteBancaire compteBancaire, Double nouveauSolde, Double nouveauCompteSpecific);

	void SupprimerCompteBancaire(Long compteBancaireId);

	CompteBancaire ConsulterCompteBancaire(CompteBancaire compteBancaire) throws CompteBancaireUnfoundException;


}
