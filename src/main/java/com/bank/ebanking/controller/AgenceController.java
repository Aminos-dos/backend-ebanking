package com.bank.ebanking.controller;

import com.bank.ebanking.model.Admin;
import com.bank.ebanking.model.Agence;
import com.bank.ebanking.service.AgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/agence")
public class AgenceController {

    AgenceService agenceService;

    @Autowired
    public AgenceController(AgenceService agenceService) {
        this.agenceService = agenceService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAgence(@RequestBody Agence agence) {
        agenceService.addAgence(agence);
    }
}
