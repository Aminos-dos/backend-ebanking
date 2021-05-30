package com.bank.ebanking.service;

import com.bank.ebanking.exception.NotFoundException;
import com.bank.ebanking.model.Admin;
import com.bank.ebanking.model.Agence;
import com.bank.ebanking.model.Agent;
import com.bank.ebanking.repo.AdminRepository;
import com.bank.ebanking.repo.AgenceRepository;

import com.bank.ebanking.repo.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {
    @Autowired
    AgentRepository agRep;
    @Autowired
    AgenceRepository agenceRep;
    @Autowired
    AdminRepository adminRep;
    @Autowired
    AdminServices adminService;
    @Autowired
    AgenceService agenceService;

//    public AgentService(AgenceService agenceService) {
//        this.agenceService = agenceService;
//    }
//
//    public AgentService(AdminServices adminService) {
//        this.adminService = adminService;
//    }

    public void addAgent(Agent agent, String nomAgence, String cin){

        Agence agence = agenceService.getAgenceByNom(nomAgence); //affecter lagence a l'agent
        agent.setAgence(agence);
        Admin admin = adminService.getAdminByCin(cin);// affecter l'admin a l'agent
        agent.setCreationAdmin(admin);
        agent.setRole("Agent");
        agRep.save(agent);
    }
    public Agent getAgentByCin(String cin){
        return agRep.findByCin(cin).orElseThrow(()-> new NotFoundException("agent with cin "+cin+" not found"));
    }

    public List<Agent> getAllAgents(){
        return agRep.findAll();
    }

    public void updateAgent(Long id, Agent agent) {
        Agent updatedAgent = agRep.findById(id).orElseThrow(()-> new NotFoundException("Agent with id "+id+" not found"));

        if(agent.getNom()!=null && !agent.getNom().isEmpty()) updatedAgent.setNom(agent.getNom());
        if(agent.getPrenom()!=null && !agent.getPrenom().isEmpty()) updatedAgent.setPrenom(agent.getPrenom());
        if(agent.getCin()!=null && !agent.getCin().isEmpty()) updatedAgent.setCin(agent.getCin());
        if(agent.getTelephone()!=null && !agent.getTelephone().isEmpty()) updatedAgent.setTelephone(agent.getTelephone());
        if(agent.getAdresse()!=null && !agent.getAdresse().isEmpty()) updatedAgent.setAdresse(agent.getAdresse());
        if(agent.getUsername()!=null && !agent.getUsername().isEmpty()) updatedAgent.setUsername(agent.getUsername());
        if(agent.getEmail()!=null && !agent.getEmail().isEmpty()) updatedAgent.setEmail(agent.getEmail());
        if(agent.getPassword()!=null && !agent.getPassword().isEmpty()) updatedAgent.setPassword(agent.getPassword());
        if(agent.getCreationAdmin()!=null ) updatedAgent.setCreationAdmin(agent.getCreationAdmin());
        if(agent.getAgence()!=null) updatedAgent.setAgence(agent.getAgence());

        agRep.save(updatedAgent);
    }
    public Agent getAgentById(long id) {
    	return agRep.findById(id).orElseThrow(()-> new NotFoundException("agent with id "+id+" not found"));
    }
}
