package com.bank.ebanking.service;

import com.bank.ebanking.exception.NotFoundException;
import com.bank.ebanking.model.Admin;
import com.bank.ebanking.model.Agence;
import com.bank.ebanking.model.Agent;
import com.bank.ebanking.model.Client;
import com.bank.ebanking.model.Compte;
import com.bank.ebanking.repo.AgenceRepository;
import com.bank.ebanking.repo.ClientRepository;
import com.bank.ebanking.repo.CompteRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class ClientService {
    @Autowired
    AgentService agentService;
    @Autowired
    AgenceService agenceService;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CompteRepository compteRepository;

    public void addClient(Client client, String nomAgence, String cin){

        Agence agence = agenceService.getAgenceByNom(nomAgence); //affecter lagence a l'agent
        client.setAgence(agence);
        Agent agent = agentService.getAgentByCin(cin);// affecter l'admin a l'agent
        client.setCreationAgent(agent);
        client.setRole("Client");
        clientRepository.save(client);
    }
    public Client getClientById(long id) {
    	return clientRepository.findById(id).orElseThrow(()-> new NotFoundException("Client with id "+id+"not found"));
    }

}
