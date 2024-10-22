package com.fss.Digital.Banking.services;

import com.fss.Digital.Banking.entites.Operation;
import com.fss.Digital.Banking.exceptions.CompteBancaireUnfoundException;
import com.fss.Digital.Banking.exceptions.SoldeInsuffisantException;

public interface OperationService {
	void crediter(Long compteBancaireId, double montant) throws CompteBancaireUnfoundException;

	void debiter(Long compteBancaireId, double montant) throws CompteBancaireUnfoundException, SoldeInsuffisantException;

	void virement(Long idCompteSource, long idCompteDestination, double montant)
			throws CompteBancaireUnfoundException, SoldeInsuffisantException;
	Operation ConsulterCompteOperation(Long compteBancaireId); 

}
