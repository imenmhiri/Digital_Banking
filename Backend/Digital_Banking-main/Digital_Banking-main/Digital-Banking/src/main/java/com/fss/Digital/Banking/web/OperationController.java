package com.fss.Digital.Banking.web;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fss.Digital.Banking.entites.CompteBancaire;
import com.fss.Digital.Banking.exceptions.CompteBancaireUnfoundException;
import com.fss.Digital.Banking.exceptions.SoldeInsuffisantException;
import com.fss.Digital.Banking.services.OperationService;

@RestController
public class OperationController {
	@Autowired
	private OperationService operationServiceImpl;

	@PostMapping("/comptebancaire/debit")
	//@PostAuthorize("hasAuthority('USER')")
	public void debit(@RequestParam Long compteId,@RequestParam double montant)
			throws CompteBancaireUnfoundException, SoldeInsuffisantException {
		operationServiceImpl.debiter(compteId, montant);
	}

	@PostMapping("/comptebancaire/credit")
	//@PostAuthorize("hasAuthority('USER')")
	public void credit(@RequestParam Long compteId,@RequestParam double montant)
			throws CompteBancaireUnfoundException {
		operationServiceImpl.crediter(compteId, montant);
	}

	@PostMapping("/comptebancaire/transfer")
	//@PostAuthorize("hasAuthority('USER')")
	public void transfer( @RequestParam Long idCompteSource,
			@RequestParam long idCompteDestination, @RequestParam double montant)
			throws CompteBancaireUnfoundException, SoldeInsuffisantException {
		operationServiceImpl.virement(idCompteSource, idCompteDestination, montant);

	}
	/*@GetMapping("/comptebancaire/{compteBancaireId}/historique")
	public CompteBancaire consulterHistoriqueCompteBancairre(@PathVariable Long compteBancaireId) {
		return operationServiceImpl.ConsulterCompteOperation(compteBancaireId); 
	}*/

}
