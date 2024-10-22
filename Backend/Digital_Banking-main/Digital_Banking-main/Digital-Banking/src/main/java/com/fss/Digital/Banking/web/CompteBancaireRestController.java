package com.fss.Digital.Banking.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fss.Digital.Banking.entites.CompteBancaire;

import com.fss.Digital.Banking.exceptions.ClientUnfoundException;
import com.fss.Digital.Banking.exceptions.CompteBancaireUnfoundException;
import com.fss.Digital.Banking.services.GestionnaireDesComptesBancairesServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CompteBancaireRestController {
	@Autowired
	private GestionnaireDesComptesBancairesServiceImpl compteBancaireServiceImpl;

	@PostMapping("/comptesbancaires")
	//@PostAuthorize("hasAuthority('ADMIN')")
	public void SaveCompte(@RequestParam double solde, @RequestParam Long clientId, @RequestParam double compteSpecific,
			@RequestParam String type) throws ClientUnfoundException, CompteBancaireUnfoundException {
		compteBancaireServiceImpl.EnregistrerCompteBancaire(solde, clientId, compteSpecific, type);
		log.info("Enrigestrement d\'un nouveau compte");

	}

	@GetMapping("/comptebancaire")
	//@PostAuthorize("hasAuthority('ADMIN')")
	public List<CompteBancaire> ConsulterTousComptesBancaires() {
		return compteBancaireServiceImpl.RechercheTousComptesBancaire();
	}

	@GetMapping("/comptebancaire/{compteBancaireId}")
	//@PostAuthorize("hasAuthority('USER')")
	public CompteBancaire ConsulterUnCompteBancaire(@PathVariable Long compteBancaireId)
			throws CompteBancaireUnfoundException {
		return compteBancaireServiceImpl.RechercheCompteBancaire(compteBancaireId);
	}

	@PutMapping("/comptebancaire/{compteBancaireId}")
	//@PostAuthorize("hasAuthority('ADMIN')")
	public void ModifierUnCompteBancaire(@PathVariable Long compteBancaireId,
			@RequestParam(required = false) Double nouveauSolde,
			@RequestParam(required = false) Double nouveauCompteSpecific) throws CompteBancaireUnfoundException {
		compteBancaireServiceImpl.ModifierClient(compteBancaireId, nouveauSolde, nouveauCompteSpecific);
		log.info("Modification du compte effectuée");

	}

	@DeleteMapping("/comptebancaire/{compteBancaireId}")
	///@PostAuthorize("hasAuthority('ADMIN')")
	public void DeleteCompteBancaire(@PathVariable Long compteBancaireId) throws CompteBancaireUnfoundException {
		compteBancaireServiceImpl.SupprimerCompteBancaire(compteBancaireId);
		log.info("Suppression du compte effectuée");

	}

}
