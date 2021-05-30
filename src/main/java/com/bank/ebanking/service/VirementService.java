package com.bank.ebanking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.ebanking.model.Compte;
import com.bank.ebanking.model.Virement;
import com.bank.ebanking.repo.CompteRepository;
import com.bank.ebanking.repo.VirementRepository;

@Service
public class VirementService {
	@Autowired
	VirementRepository virementRepository;
	@Autowired
	CompteRepository compteRep;
	@Autowired
	CompteService compteService;
	public void addVirement(Virement virement,long idCreancier,long idDebiteur) {
		Compte creancier = compteService.getById(idCreancier);
		Compte debiteur = compteService.getById(idDebiteur);
		virement.setCreancier(creancier);
		virement.setDebiteur(debiteur);
		virement.setDate(LocalDateTime.now());
		virementRepository.save(virement);
		creancier.setSolde(creancier.getSolde()+virement.getSommeRecu());
		debiteur.setSolde(debiteur.getSolde()-virement.getSommeEnv());
		compteRep.save(creancier);
		compteRep.save(debiteur);
	}
	public List<Virement> getAllVirEnv(long idCompte){
		Compte compte = compteService.getById(idCompte);
		return virementRepository.findByDebiteur(compte);
	}
	public List<Virement> getAllVirRec(long idCompte){
		Compte compte = compteService.getById(idCompte);
		List<Virement> lalist = virementRepository.findByCreancier(compte);
		return lalist;
	}
	
}
