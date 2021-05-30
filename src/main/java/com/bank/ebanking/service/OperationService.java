package com.bank.ebanking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.ebanking.model.Compte;
import com.bank.ebanking.model.Operation;
import com.bank.ebanking.repo.CompteRepository;
import com.bank.ebanking.repo.OperationRepository;

@Service
public class OperationService {
	@Autowired
	OperationRepository opeRep;
	@Autowired
	CompteRepository compteRep;
	@Autowired
	CompteService compteService;
	
	public void addOperation(Operation operation,long idCompte) {
		Compte compte = compteService.getById(idCompte);
		operation.setCompte(compte);
		operation.setDate(LocalDateTime.now());
		opeRep.save(operation);
		//compte.getOperations().add(operation);
		//compteRep.save(compte);
	}
	public List<Operation> getAllOper(long idCompte){
		Compte compte = compteService.getById(idCompte);
		return opeRep.findByCompte(compte);
	}

}
