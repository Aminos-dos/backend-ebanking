package com.bank.ebanking.controller;

import com.bank.ebanking.model.Agent;
import com.bank.ebanking.model.Client;
import com.bank.ebanking.model.Compte;
import com.bank.ebanking.service.ClientService;
import com.bank.ebanking.service.CompteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/client")
public class ClientController {

    ClientService clientService;
    CompteService compteService;
    
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @PostMapping("/add/{cin}/{nomAgence}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAdmin(@RequestBody Client client, @PathVariable(name="cin") String cin, @PathVariable(name="nomAgence") String nomAgence  ) {
        clientService.addClient(client, nomAgence, cin);
    }
    

}
