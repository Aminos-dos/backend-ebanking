package com.bank.ebanking.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.ebanking.exception.NotFoundException;
import com.bank.ebanking.model.Agent;
import com.bank.ebanking.model.Client;
import com.bank.ebanking.model.Compte;
import com.bank.ebanking.repo.ClientRepository;
import com.bank.ebanking.repo.CompteRepository;

@Service
public class CompteService {
	@Autowired
	CompteRepository compteRep;
	@Autowired
	ClientRepository clientRep;
	@Autowired
	AgentService agentService;
	@Autowired
	ClientService clientService;
	public Compte getById(long id) {
		return compteRep.findById(id).orElseThrow(()-> new NotFoundException("Compte with id "+id+" not found"));
	}
	public void addCompte(Compte compte,long idClient,long idAgent) {
		Client client = clientService.getClientById(idClient);
		Agent agent = agentService.getAgentById(idAgent);
		compte.setCreationAgent(agent);
		compte.setProprietaire(client);
		compte.setCreationDate(LocalDateTime.now());
		compteRep.save(compte);
		
	}
	public List<Compte> getComptes(long idClient){
		Client client = clientRep.findById(idClient).orElseThrow(()-> new NotFoundException("Client with id "+idClient+" not found"));
		return compteRep.findByProprietaire(client);
	}
	
	
}
